package com.zxp.user.controller;


import com.zxp.user.params.UserParams;
import com.zxp.user.params.UserQueryParams;
import com.zxp.user.resoponse.Result;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController{

    public Result<UserVo> findById(String id) {
        return null;
    }


    public Result<Boolean> existsById(String id) {
        return null;
    }


    public Result<Boolean> existsByQuery(UserQueryParams userQueryParams) {
        return null;
    }


    public Result<UserVo> registerUser(UserParams user) {
        return null;
    }

    public Result<UserVo> unRegisterUser(String id) {
        return null;
    }

}
