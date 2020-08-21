package com.zxp.controller;

import com.zxp.SpringbootLearningApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootLearningApplication.class)
public class ResponseSetStatusTest {
    @Autowired
    private ResponseSetStatus responseSetStatus;

    @Test
    public void TestResponseEntry() throws UnsupportedEncodingException {
        ResponseEntity<byte[]> responseEntity = responseSetStatus.returnResponseEntry();
        System.out.println(responseEntity.getHeaders().get("Content-MD5"));
    }

    @Test
    public void TestResponse() throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        //采用测试的HttpServletResponse
        HttpServletResponse httpServletResponse =new MockHttpServletResponse();
        System.out.println(responseSetStatus.returnResponseStatus(httpServletResponse, headers));
    }
}