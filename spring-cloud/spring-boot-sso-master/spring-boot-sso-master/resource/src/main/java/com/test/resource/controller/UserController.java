package com.test.resource.controller;

import com.test.mysql.entity.User;
import com.test.mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private final UserRepository userRepository;
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/user")
    public Map<String, Object> user(Principal puser) {
        User user = userRepository.findByName(puser.getName());
        Map<String, Object> ulcering = new HashMap<>();
        ulcering.put("id", user.getId());
        ulcering.put("name",user.getName());
        ulcering.put("email", user.getEmail());
        ulcering.put("department",user.getDepartment().getName());
        ulcering.put("createdate", user.getCreatedate());
        return ulcering;
    }
}
