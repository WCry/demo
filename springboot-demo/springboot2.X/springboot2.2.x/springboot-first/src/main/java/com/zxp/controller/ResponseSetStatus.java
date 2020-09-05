package com.zxp.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RestController
public class ResponseSetStatus {
    @GetMapping(value = "/responseEntry", produces={MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<byte[]> returnResponseEntry() throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-MD5", "8ac8489932db6327334c9b6d58544cfe");
        byte[] bytes = "8ac8489932db6327334c9b6d58544cfe".getBytes("utf8");
        ResponseEntity responseEntity = new ResponseEntity(bytes, headers, HttpStatus.ACCEPTED);
        return responseEntity;
    }
    @PostMapping("/response")
    public byte[] returnResponseStatus(HttpServletResponse httpResponse,HttpHeaders headers) throws UnsupportedEncodingException {
        headers.set("Content-MD5", "8ac8489932db6327334c9b6d58544cfe");
        byte[] bytes = "8ac8489932db6327334c9b6d58544cfe".getBytes("utf8");
        httpResponse.setStatus(HttpStatus.ACCEPTED.value());
        return bytes;
    }

}
