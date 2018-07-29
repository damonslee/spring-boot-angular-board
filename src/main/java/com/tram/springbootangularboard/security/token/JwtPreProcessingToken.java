package com.tram.springbootangularboard.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtPreProcessingToken extends UsernamePasswordAuthenticationToken {
    //권한 정보가 없는 생성자로 인증 전 객체 생성
    private JwtPreProcessingToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtPreProcessingToken(String token) {
        super(token, token.length());
    }
}
