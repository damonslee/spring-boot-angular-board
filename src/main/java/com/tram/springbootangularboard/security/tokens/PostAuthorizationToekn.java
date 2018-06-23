package com.tram.springbootangularboard.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PostAuthorizationToekn extends UsernamePasswordAuthenticationToken {
    public PostAuthorizationToekn(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
