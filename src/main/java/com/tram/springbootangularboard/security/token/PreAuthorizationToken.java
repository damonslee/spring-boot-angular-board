package com.tram.springbootangularboard.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {
    public PreAuthorizationToken(String userId, String password) {
        super(userId, password);
    }

    public String getUserId() {
        return (String)super.getPrincipal();
    }

    public String getPassword() {
        return (String)super.getCredentials();
    }
}
