package com.example.datamong.controller;


import com.example.datamong.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MongoDBController {
    @Autowired
    private DataService dataService;
    @GetMapping("tms/{dataDomain}/{serverName}/{z}/{x}/{y}")
    public byte[] getTMSTile(@PathVariable("dataDomain") String dataDomain,
                           @PathVariable("serverName") String serverName,
                           @PathVariable("z") String z,
                           @PathVariable("x") String x,
                           @PathVariable("y") String y, HttpServletResponse response) throws Exception {
        String pathStr=dataDomain+"/"+serverName+"/"+z+"/"+x+"/"+y+".png";
        System.out.println(pathStr);
       return  dataService.findDataByPath(pathStr);
//        InputStream inputStream = dataService.findDataByPath(pathStr);
//        response.setContentType("image/png");
//        response.setContentType(URLConnection.guessContentTypeFromName(pathStr));
//        IOUtils.copy(inputStream, response.getOutputStream());
//        response.flushBuffer();
    }


//    @PostMapping("file/upload")
//    public void addAttachement(@RequestParam("file") MultipartFile file) throws Exception {
//        System.out.println(file);
//        String filename = file.getOriginalFilename();
//        filename= "tms/12/"+filename;
//        dataService.saveData(filename, file.getInputStream(), file.getContentType());
//
//    }
}
