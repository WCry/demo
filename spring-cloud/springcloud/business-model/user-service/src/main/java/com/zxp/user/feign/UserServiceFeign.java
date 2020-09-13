package com.zxp.user.feign;


import com.zxp.user.api.UserFeignAPI;
import com.zxp.user.params.dto.UserDTO;
import com.zxp.user.params.query.UserIdentifyQuery;
import com.zxp.user.params.register.UserRegisterParams;
import com.zxp.user.params.query.UserBaseQuery;
import com.zxp.user.po.UserDO;
import com.zxp.user.resoponse.Result;
import com.zxp.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class UserServiceFeign implements UserFeignAPI {
    private final UserService userService;

    public UserServiceFeign(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Result<Optional<UserDTO>> queryUserByIdentify(UserIdentifyQuery userIdentifyQuery) {
        return Result.success(this.userService.findUserDTOById(userIdentifyQuery.getOpenID()));
    }

    @Override
    public Result<List<UserDTO>> queryUserByBaseInfo(UserBaseQuery userBaseQuery) {
        return null;
    }

    @Override
    public Result<Boolean> existsByIdentify(UserIdentifyQuery userIdentifyQuery) {
        return Result.success(this.userService.existsById(userIdentifyQuery.getOpenID()));
    }

    @Override
    public Result<Boolean> existsByBaseQuery(UserBaseQuery userBaseQuery) {
        return Result.success(this.userService.existsByQuery(userBaseQuery));
    }

    @Override
    public Result<Boolean> updateUser(UserIdentifyQuery userIdentifyQuery, UserRegisterParams userRegisterParams) {
        return null;
    }

    @Override
    public Result<Boolean> registerUser(UserRegisterParams userRegisterParams) {

        return Result.success(this.userService.registerUser(userRegisterParams,Optional.empty()));
    }

    @Override
    public Result<Boolean> unRegisterUser(UserIdentifyQuery userIdentifyQuery) {
        return Result.success(this.userService.unRegisterUser(userIdentifyQuery.getOpenID()));
    }
}
