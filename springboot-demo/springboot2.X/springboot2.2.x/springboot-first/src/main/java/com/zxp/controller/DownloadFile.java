package com.zxp.controller;

import org.apache.tomcat.util.http.parser.Ranges;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class DownloadFile {
    @GetMapping(value = "dowload",produces =
            MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResourceRegion downloadResourceRegion(@Nullable @RequestHeader(HttpHeaders.RANGE) StringReader range) throws IOException {
        if(!Objects.isNull(range)){
           //解析Range参数
            Ranges ranges= Ranges.parse(range);
            for (Ranges.Entry entry : ranges.getEntries()) {
                System.out.println("start:"+entry.getStart()+"end:"+entry.getEnd());
            }
        }
        String result="downLoadResource";
        Resource resource= new ByteArrayResource(result.getBytes(),"test");
        ResourceRegion resourceRegion= new ResourceRegion(resource,0, resource.contentLength());
//        rsp.setHeader("Content-Disposition",
        //        "attachment;fileName=" +
        //        java.net.URLEncoder.encode(fileInfo.getName(), "UTF-8"));
//        rsp.setCharacterEncoding("utf-8");
        //需要另外设置头设置下载文件名称
        //ResourceRegion 没有在头部设置下在文件的名称
        //解析是通过ResourceRegionHttpMessageConverter进行解析返回Response
        return resourceRegion;
    }
}
