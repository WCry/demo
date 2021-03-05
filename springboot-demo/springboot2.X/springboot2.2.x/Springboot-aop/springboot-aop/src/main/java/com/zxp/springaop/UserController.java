package com.zxp.springaop;

/**
 * @author zhangxuepei
 * @since 3.0
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value="/" ,method = RequestMethod.GET)
    public void saveUser(String name,String sex){
        userService.saveUser(name,sex);
    }
}
