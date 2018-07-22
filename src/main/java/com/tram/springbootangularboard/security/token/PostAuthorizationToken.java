package com.tram.springbootangularboard.security.token;

import com.tram.springbootangularboard.security.AccountContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PostAuthorizationToken extends UsernamePasswordAuthenticationToken {

    public PostAuthorizationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public AccountContext getAccountContext() {
        return (AccountContext)super.getPrincipal();
    }

    public static PostAuthorizationToken getFromAccountContext(AccountContext accountContext) {
        return new PostAuthorizationToken(accountContext, accountContext.getPassword(), accountContext.getAuthorities());
    }
}
