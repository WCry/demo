package com.zxp.user.feign;


import com.zxp.user.UserFeignAPI;
import com.zxp.user.dto.UserDTO;
import com.zxp.user.params.UserParams;
import com.zxp.user.params.UserQueryParams;
import com.zxp.user.po.UserDO;
import com.zxp.user.resoponse.Result;
import com.zxp.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class UserServiceFeign implements UserFeignAPI {
    private final UserService userService;

    public UserServiceFeign(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Result<Optional<UserDTO>> findById(String id) {
        return Result.success(this.userService.findUserDTOById(id));
    }

    @Override
    public Result<Boolean> existsById(String id) {
        return null;
    }

    @Override
    public Result<Boolean> existsByQuery(UserQueryParams userQueryParams) {
        return null;
    }

    @Override
    public Result<Boolean> registerUser(UserParams userParams) {
        UserDO userDO =new UserDO();
        BeanUtils.copyProperties(userParams, userDO);
        this.userService.registerUser(userDO);
        return null;
    }

    @Override
    public Result<Boolean> unRegisterUser(String id) {
        return null;
    }

}
