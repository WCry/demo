package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping(value="/rest")
public class HelloColler {
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @ResponseBody
    public String index(@RequestParam("abc") String abc) {
       // System.out.println(">>>>>>>>>>>>>>>>>>>>>login>>>>>>>>>>>>>>>>>>>>>>");
        return "Hello World";
    }
    @GetMapping(value = "/hello23")
    public String indexds(Model model) {
        System.out.println(">>login>>>>");
        model.addAttribute("title","dsad");
        return "/test";
    }
}
