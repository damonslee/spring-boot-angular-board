package com.tram.springbootangularboard.security.token;

import com.tram.springbootangularboard.security.AccountContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PostAuthorizationToekn extends UsernamePasswordAuthenticationToken {

    public PostAuthorizationToekn(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public static PostAuthorizationToekn getFromAccountContext(AccountContext accountContext) {
        return new PostAuthorizationToekn(accountContext, accountContext.getPassword(), accountContext.getAuthorities());
    }
}
