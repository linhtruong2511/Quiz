package com.Eddie.Quiz.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/")
    String test (@AuthenticationPrincipal OAuth2User user) {
        if(user == null) return "user is null";
        String name = user.getAttribute("name");
        String email = user.getAttribute("email");
        System.out.println(name);
        return "hello " + name;
    }

    @GetMapping("/test")
    String test2 (@AuthenticationPrincipal OAuth2User user) {
        if(user == null) return "user is null";
        String name = user.getAttribute("name");
        String email = user.getAttribute("email");
        String url = user.getAttribute("picture");
        System.out.println(name);
        return "chúc mừng bạn đã đăng nhập thành công với email : " + email + " anh đại diện của bạn là: " + url;
    }
}
