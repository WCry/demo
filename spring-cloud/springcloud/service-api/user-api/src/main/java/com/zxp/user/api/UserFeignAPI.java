package com.zxp.user.api;

import com.zxp.user.params.dto.UserDTO;
import com.zxp.user.params.query.UserIdentifyQuery;
import com.zxp.user.params.update.UserSecurityParams;
import com.zxp.user.params.query.UserBaseQuery;
import com.zxp.resoponse.Result;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;


public interface UserFeignAPI {
    /**
     * 通过用户唯一标识查询用户信息
     * @param userIdentifyQuery
     * @return
     */
    @GetMapping("/identify/query")
    Result<Optional<UserDTO>> queryUserByIdentify(UserIdentifyQuery userIdentifyQuery);
    /**
     * 通过用户的注册的基本信息中过滤
     * @param userBaseQuery
     * @return
     */
    @GetMapping("/userBase/query")
    Result<List<UserDTO>> queryUserByBaseInfo(UserBaseQuery userBaseQuery);

    /**
     * 通过用户的唯一标识查询用户的基本信息
     *
     * @param userIdentifyQuery
     * @return
     */
    @GetMapping("/identify/exists")
    Result<Boolean> existsByIdentify(UserIdentifyQuery userIdentifyQuery);

    /**
     * 通过注册信息查询是否注册过
     *
     * @param userBaseQuery
     * @return
     */
    @GetMapping("/userBase/exists")
    Result<Boolean> existsByBaseQuery(UserBaseQuery userBaseQuery);
    /**
     * 更新用户信息
     * @param userRegisterParams
     * @return
     */
    @GetMapping("/update")
    Result<Boolean> updateUser(UserIdentifyQuery userIdentifyQuery, UserSecurityParams userRegisterParams);
    /**
     * 注册用户
     * @param userRegisterParams
     * @return
     */
    @GetMapping("/register")
    Result<Boolean> registerUser(UserSecurityParams userRegisterParams);

    /**
     * 解除注册
     *
     * @param userIdentifyQuery
     * @return
     */
    @GetMapping("/unRegister")
    Result<Boolean> unRegisterUser(UserIdentifyQuery userIdentifyQuery);

}
