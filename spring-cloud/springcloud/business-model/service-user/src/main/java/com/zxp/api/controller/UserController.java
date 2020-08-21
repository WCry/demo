package com.zxp.api.controller;

import com.zxp.api.user.UserFeignAPI;
import com.zxp.api.user.params.UserParams;
import com.zxp.api.user.params.UserQueryParams;
import com.zxp.api.user.vo.UserVo;
import com.zxp.resoponse.Result;


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
