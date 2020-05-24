package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Login {
    @GetMapping(value = "/")
    public String index() {
        return "login";
    }
}
