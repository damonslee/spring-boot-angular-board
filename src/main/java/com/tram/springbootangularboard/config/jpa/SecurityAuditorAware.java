package com.tram.springbootangularboard.config.jpa;

import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.security.token.PostAuthorizationToken;
import com.tram.springbootangularboard.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityAuditorAware implements AuditorAware<Account> {
    @Autowired
    private AccountService accountService;
    @Override
    public Optional<Account> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        PostAuthorizationToken postAuthorizationToken = (PostAuthorizationToken)authentication;
        return Optional.ofNullable(accountService.findByEmail(postAuthorizationToken.getAccountContext().getAccount().getEmail()));
    }
}
