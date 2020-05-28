package com.example.springjpademo.controller;


import com.example.springjpademo.projo.User;
import com.example.springjpademo.repositorie.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserRepository repository;

    //根据编号查询
    @PostMapping(value = "/findById")
    User findById(@RequestParam(value = "id") Long id) {
        return repository.findById(id).get();
    }
    //
    //    @PostMapping(value = "findName")
    //    List<User> findName(@RequestParam(value = "name") String name) {
    //        return repository.findByNameLike(name);
    //    }
    //
    //    @PostMapping(value = "findAll")
    //        //currentPage 当前页，默认为0，如果传1的话是查的第二页数据
    //        //pageSize 每页数据条数
    //    Page<User> findAll(@RequestParam(value = "currentPage") int currentPage, @RequestParam(value = "pageSize") int pageSize) {
    //        Pageable pageable = PageUtil.getPageable(currentPage, pageSize, "datetime");
    //        return repository.findAll(pageable);
    //    }
}
