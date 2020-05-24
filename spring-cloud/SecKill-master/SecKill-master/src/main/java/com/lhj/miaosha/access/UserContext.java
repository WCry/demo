package com.lhj.miaosha.access;

import com.lhj.miaosha.domain.MiaoshaUser;

public class UserContext {

    private static ThreadLocal<MiaoshaUser> userThreadLocal = new ThreadLocal<MiaoshaUser>();

    /**
     * 在一个请求  预处理拦截器中进行处理 设置进去User
     * @param user
     */
    public static void setUser(MiaoshaUser user){
        userThreadLocal.set(user);
    }

    /**
     *  在方法参数解析的时候将 user 获取到
     * @return
     */
    public static MiaoshaUser gerUser(){
        return userThreadLocal.get();
    }
}
