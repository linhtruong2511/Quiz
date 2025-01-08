package com.Eddie.Quiz.controller;

import com.Eddie.Quiz.dto.request.UserLoginRequest;
import com.Eddie.Quiz.dto.request.UserRegisterRequest;
import com.Eddie.Quiz.dto.response.ResponseAPI;
import com.Eddie.Quiz.dto.response.UserRegisterResponse;
import com.Eddie.Quiz.entity.UserEntity;
import com.Eddie.Quiz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    @GetMapping("/auth/verifier/{token}")
    ResponseEntity<?> verifierToken(@PathVariable String token) {
        return ResponseEntity.ok(authService.verifierToken(token));
    }

    @PostMapping("/auth/username-login")
    ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        String token = authService.login(request);
        ResponseAPI<String> responseAPI = ResponseAPI.<String>builder()
                .data(token)
                .status(HttpStatus.OK.name())
                .build();
        return ResponseEntity.ok(responseAPI);
    }

    @PostMapping("/auth/register")
    ResponseEntity<?> register(@RequestBody UserRegisterRequest request){
        UserRegisterResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/auth/my-info")
    ResponseEntity<?> getMyInfo(){
        UserEntity user = authService.getMyInfo();
        ResponseAPI<UserEntity> responseAPI = ResponseAPI.<UserEntity>builder()
                .data(user)
                .status(HttpStatus.OK.toString())
                .build();

        return ResponseEntity.ok().body(responseAPI);
    }
}
