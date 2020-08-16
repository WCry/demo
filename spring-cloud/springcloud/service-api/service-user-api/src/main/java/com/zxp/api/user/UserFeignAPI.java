package com.zxp.api.user;

import com.zxp.api.user.params.UserParams;
import com.zxp.api.user.pojo.UserQueryParams;
import com.zxp.api.user.vo.UserVo;
import com.zxp.resoponse.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface UserFeignAPI {
    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    Result<UserVo> findById(@PathVariable(name = "id") String id);

    /**
     * 通过OpenID查询用户是否存在
     *
     * @param id
     * @return
     */
    @GetMapping("/exists/{id}")
    Result<Boolean> existsById(@PathVariable(name = "id") String id);

    /**
     * 通过注册信息查询是否注册过
     *
     * @param userQueryParams
     * @return
     */
    @GetMapping("/exists/query")
    Result<Boolean> existsByQuery(UserQueryParams userQueryParams);

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @GetMapping("/register")
    Result<UserVo> registerUser(UserParams user);

    /**
     * 解除注册
     *
     * @param id
     * @return
     */
    @GetMapping("/unRegister")
    Result<UserVo> unRegisterUser(String id);


}
