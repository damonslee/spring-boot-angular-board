package com.tram.springbootangularboard.security.provider;

import com.tram.springbootangularboard.security.tokens.PreAuthorizationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class FormLoginAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //PreAuthorizationToken으로 들어오는 요청에 대해서 필터에 걸리게 된다.
        return PreAuthorizationToken.class.isAssignableFrom(authentication);
    }
}
