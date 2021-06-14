package com.zxp;

/**
 * @author zhangxuepei
 * @since 3.0
 */
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void saveUser(String name, String sex) {
        System.out.println("name = " + name + ", sex = " + sex+" id =");
        sayHello(name);
    }

    public void sayHello(String name){
        System.out.println("Hello name = " + name+" Welcome in Spring Interceptor world");
    }
}
