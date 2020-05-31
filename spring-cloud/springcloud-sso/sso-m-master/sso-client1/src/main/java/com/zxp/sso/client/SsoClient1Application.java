//package com.zxp.sso.client;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@SpringBootApplication
//@RestController
//@EnableOAuth2Sso
//public class SsoClient1Application {
//
//    final OAuth2RestTemplate oAuth2RestTemplate;
//    @Autowired
//    public SsoClient1Application(OAuth2RestTemplate oAuth2RestTemplate) {this.oAuth2RestTemplate = oAuth2RestTemplate;}
//
//    @GetMapping("/user")
//    public Authentication user(Authentication user) {
//        return user;
//    }
//    @GetMapping("/user2")
//    public String user2() {
//        return "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
//    }
//    @GetMapping("/aaa")
//    public String a() {
//        return "qaaaaaaaaaaaaaaaaaaaaakkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
//    }
//    @GetMapping("/bbb")
//    public String b() {
//        return "bbbbbbbbbbbbbbbbbbbkkkkkkkkkkkkkkkkkkkkkkkkkkk";
//    }
//
//    @Value("${messages.url:http://localhost:8085}/resource/api")
//    String messagesUrl;
//
//    public static void main(String[] args) {
//        SpringApplication.run(SsoClient1Application.class, args);
//    }
//
//    @RequestMapping("/api")
//    String home(Model model) {
//        String result = oAuth2RestTemplate.getForObject(messagesUrl + "/2", String.class);
//        return result;
//    }
//    @PostMapping("/login")
//    public String dsdb() {
//        return "bbbbbbbbbbbbbbbbbbbkkkkkkkkkkkkkkkkkkkkkkkkkkk";
//    }
//}
