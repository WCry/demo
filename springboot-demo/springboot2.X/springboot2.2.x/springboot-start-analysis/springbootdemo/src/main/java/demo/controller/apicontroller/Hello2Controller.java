package demo.controller.apicontroller;

import demo.apiversion.ApiVersion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by jack 2018/8/19
 *
 * @auther jack
 * @date: 2018/8/19 10:52
 * @Description:
 */
@ApiVersion(2)
@RestController
@RequestMapping("{version}/hello")
public class Hello2Controller {

    @GetMapping("/world")
    public String helloWorld(){
        System.out.println("版本是2的接口");
        return "hello,world .version is 2";
    }

}

