package depective;

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
@RequestMapping("{version}/hello2")
public class Hello2Controller {

    @RequestMapping("/world")
    public String helloWorld(){
        System.out.println("版本是2的接口");
        return "hello,world .version is 2我这里就是测试下了";
    }

}

