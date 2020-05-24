package com.example.srpingclouddemo.eurekaserver;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Texst {
    @RequestMapping(value = "/testMap",method = RequestMethod.POST)
   public String testMap(@RequestBody  Map<String,Object> dasd){
       System.out.println(dasd.get("dasddas"));
       return dasd.get("dasddas").toString();
   }
}
