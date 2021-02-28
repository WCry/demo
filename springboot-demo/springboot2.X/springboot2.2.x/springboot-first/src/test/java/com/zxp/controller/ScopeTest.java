package com.zxp.controller;

import com.zxp.SpringbootLearningApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@SpringBootTest(classes= SpringbootLearningApplication.class)
public class ScopeTest {
    @Autowired
    private WebScopeController webScopeController;

    @Test
    public void TestResponseEntry() throws UnsupportedEncodingException {
        webScopeController.getRequestScope();
    }
}