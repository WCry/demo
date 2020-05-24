package depective;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/demosest")
public class helloColler {
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @ResponseBody
    public String index(@RequestParam("abc") String abc) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>login>>>>>>>>>>>>>>>>>>>>>>");
        return "Hello World  我这里就是测试下了";
    }
    @RequestMapping(value = "/hello23", method = RequestMethod.GET)
    @ResponseBody
    public String indexds(String abc) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>login>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(abc);
        return "Hello World  我这里就是测试下了";
    }
}
