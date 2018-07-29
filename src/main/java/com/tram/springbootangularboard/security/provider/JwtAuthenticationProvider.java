package com.tram.springbootangularboard.security.provider;

import com.tram.springbootangularboard.security.JwtDecoder;
import com.tram.springbootangularboard.security.token.JwtPreProcessingToken;
import com.tram.springbootangularboard.security.token.PostAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private JwtDecoder jwtDecoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String)authentication.getPrincipal();
        return PostAuthorizationToken.getFromAccountContext(jwtDecoder.decodeToken(token));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
