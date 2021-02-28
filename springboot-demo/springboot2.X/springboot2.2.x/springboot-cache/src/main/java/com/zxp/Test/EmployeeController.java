package com.zxp.Test;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
   @Value("${zxp}")
   private String ddd;
   EmployeeController(){

   }
   @GetMapping("/testGetRequestValue")
   public String getRequestScope() {
      System.out.println(ddd);
      return "获取到的Request的Value是";
   }
}