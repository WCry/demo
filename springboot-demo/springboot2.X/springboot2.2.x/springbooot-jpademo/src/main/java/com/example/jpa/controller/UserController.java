//package com.example.jpa.controller;
//
//
//import com.example.jpa.projo.User;
//import com.example.jpa.repository.UserRepository;
//import com.example.jpa.service.UserServiceImpl;
//import com.example.jpa.util.PageUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//    private final UserServiceImpl userService;
//    private final UserRepository repository;
//
//    public UserController(UserServiceImpl userService, UserRepository repository) {
//        this.userService = userService;
//        this.repository = repository;
//    }
//    @GetMapping(value = "/insert")
//    public void insertValue() {
//        userService.insertValue();
//    }
//
//    //根据编号查询
//    @PostMapping(value = "/findById")
//    User findById(@RequestParam(value = "id") Long id) {
//        return repository.findById(id).get();
//    }
//
//    @PostMapping(value = "findName")
//    List<User> findName(@RequestParam(value = "name") String name) {
//        return repository.findByNameLike(name);
//    }
//
//    @PostMapping(value = "findAll")
//        //currentPage 当前页，默认为0，如果传1的话是查的第二页数据
//        //pageSize 每页数据条数
//    Page<User> findAll(@RequestParam(value = "currentPage") int currentPage,
//                       @RequestParam(value = "pageSize") int pageSize) {
//        Pageable pageable = PageUtil.getPageable(currentPage, pageSize, "datetime");
//        return repository.findAll(pageable);
//    }
//}
