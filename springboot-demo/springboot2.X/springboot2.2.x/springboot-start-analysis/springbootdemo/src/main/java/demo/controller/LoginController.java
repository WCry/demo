package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    @GetMapping(value = "/")
    public String index() {
        //返回一个Model的名称
        return "login";
    }
}
