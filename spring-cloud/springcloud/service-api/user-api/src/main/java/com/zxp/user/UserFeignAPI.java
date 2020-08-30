package com.zxp.user;

import com.zxp.user.dto.UserDTO;
import com.zxp.user.params.UserParams;
import com.zxp.user.params.UserQueryParams;
import com.zxp.user.resoponse.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


public interface UserFeignAPI {
    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/load/{id}")
    Result<Optional<UserDTO>> findById(@PathVariable(name = "id") String id);

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
    Result<Boolean> registerUser(UserParams user);

    /**
     * 解除注册
     *
     * @param id
     * @return
     */
    @GetMapping("/unRegister")
    Result<Boolean> unRegisterUser(String id);


}
