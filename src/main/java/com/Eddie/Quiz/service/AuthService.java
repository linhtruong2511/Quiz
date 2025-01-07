package com.Eddie.Quiz.service;

import com.Eddie.Quiz.dto.request.UserLoginRequest;
import com.Eddie.Quiz.dto.request.UserRegisterRequest;
import com.Eddie.Quiz.dto.response.UserRegisterResponse;
import com.Eddie.Quiz.entity.UserEntity;
import com.nimbusds.jose.KeyLengthException;

public interface AuthService {
    public String generateToken(String username);
    boolean verifierToken(String token);
    String login(UserLoginRequest request);
    UserRegisterResponse register(UserRegisterRequest request);
    UserEntity getMyInfo();
}
