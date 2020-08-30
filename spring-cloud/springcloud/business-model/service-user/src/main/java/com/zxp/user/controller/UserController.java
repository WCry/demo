package com.zxp.user.controller;


import com.zxp.user.params.UserParams;
import com.zxp.user.params.UserQueryParams;
import com.zxp.user.vo.UserVo;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class UserController{

    public Optional<Boolean> findUserVoById(String id) {
        return Optional.empty();
    }


    public Boolean existsById(String id) {
        return null;
    }


    public Boolean existsByQuery(UserQueryParams userQueryParams) {
        return null;
    }


    public Boolean registerUser(UserParams user) {
        return null;
    }

    public Boolean unRegisterUser(String id) {
        return null;
    }

}
