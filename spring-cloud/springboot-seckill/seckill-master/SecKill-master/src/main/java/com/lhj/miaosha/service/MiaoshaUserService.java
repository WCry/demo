package com.lhj.miaosha.service;

import com.lhj.miaosha.dao.MiaoShaUserDao;
import com.lhj.miaosha.domain.MiaoshaUser;
import com.lhj.miaosha.exception.GlobalException;
import com.lhj.miaosha.redis.MiaoshaUserKey;
import com.lhj.miaosha.redis.RedisService;
import com.lhj.miaosha.result.CodeMsg;
import com.lhj.miaosha.util.MD5Util;
import com.lhj.miaosha.util.UUIDUtil;
import com.lhj.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：LHJ
 * @date ：2019/7/25 18:50
 * @description：${description}
 */
@Service
public class MiaoshaUserService {
    public static final String COOKIE_NAME_TOKEN = "token";
    @Autowired
    MiaoShaUserDao miaoShaUserDao;
    @Autowired
    RedisService redisService;
    public MiaoshaUser getById(long id){
        //对象缓存
        MiaoshaUser user = redisService.get(MiaoshaUserKey.getById, "" + id, MiaoshaUser.class);
        if (user != null) {
            return user;
        }
        //取数据库
        user = miaoShaUserDao.getById(id);
        //再存入缓存
        if (user != null) {
            redisService.set(MiaoshaUserKey.getById, "" + id, user);
        }
        return user;

    }

    /**
     * 典型缓存同步场景：更新密码
     */
    public boolean updatePassword(String token, long id, String formPass) {
        //取user
        MiaoshaUser user = getById(id);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //更新数据库
        MiaoshaUser toBeUpdate = new MiaoshaUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        miaoShaUserDao.update(toBeUpdate);
        //更新缓存：先删除再插入
        redisService.delete(MiaoshaUserKey.getById, ""+id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(MiaoshaUserKey.token, token, user);
        return true;
    }

    public String login(HttpServletResponse response,LoginVo loginVo){
        if (loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return token;
    }
    /**
     * 将token做为key，用户信息做为value 存入redis模拟session，键值对为（token，user）
     * 同时将token存入cookie，保存登录状态，键值对为（"token",token)
     */
    public void addCookie(HttpServletResponse response, String token , MiaoshaUser user) {
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");//设置为网站根目录
        response.addCookie(cookie);
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaUserKey.token,token,MiaoshaUser.class);
        //延长缓存key的有效期
        if(user !=null){
            addCookie(response, token, user);
        }
        return user;
    }
}
