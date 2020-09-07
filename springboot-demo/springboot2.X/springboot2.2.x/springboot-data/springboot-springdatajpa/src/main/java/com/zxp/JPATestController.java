package com.zxp;

import com.zxp.dao.UserJpaRepository;
import com.zxp.entity.po.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JPATestController {
    private final UserJpaRepository userJpaRepository;

    public JPATestController(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @GetMapping("/test/get")
    public String TestGetUser() {
        List<User> userList= userJpaRepository.findByAgeAfter(10);
        return "";
    }

}
