package com.tram.springbootangularboard.security.token;

import com.tram.springbootangularboard.dto.FormLoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {
    private PreAuthorizationToken(String userId, String password) {
        super(userId, password);
    }
    public PreAuthorizationToken(FormLoginDto formLoginDto) {
        this(formLoginDto.getUserId(), formLoginDto.getPassword());
    }
    public String getEmail() {
        return (String)super.getPrincipal();
    }

    public String getPassword() {
        return (String)super.getCredentials();
    }
}
