package spring.study.mytest.controller;

import com.zxp.loggerstarter.annotation.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Log
    @RequestMapping("/test")
    public String test() {
        return "only test";
    }

}
