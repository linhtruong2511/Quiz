package com.Eddie.Quiz.Util;

import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public class JwtUtils {
    public String getUsernameFromToken(String token){
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
