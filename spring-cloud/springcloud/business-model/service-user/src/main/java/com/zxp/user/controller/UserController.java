package com.zxp.user.controller;

import com.zxp.user.api.user.UserFeignAPI;
import com.zxp.user.api.user.params.UserParams;
import com.zxp.user.api.user.params.UserQueryParams;
import com.zxp.user.api.user.vo.UserVo;
import com.zxp.user.resoponse.Result;


public class UserController implements UserFeignAPI {
    @Override
    public Result<UserVo> findById(String id) {
        return null;
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
    public Result<UserVo> registerUser(UserParams user) {
        return null;
    }

    @Override
    public Result<UserVo> unRegisterUser(String id) {
        return null;
    }

}
