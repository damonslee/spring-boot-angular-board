package com.tram.springbootangularboard.security.provider;

import com.tram.springbootangularboard.common.CommonUtils;
import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.domain.AccountRepository;
import com.tram.springbootangularboard.security.AccountContext;
import com.tram.springbootangularboard.security.token.PostAuthorizationToken;
import com.tram.springbootangularboard.security.token.PreAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class FormLoginAuthenticationProvider implements AuthenticationProvider {
    //TODO AccountContextService 사용하도록 리팩토링을 해야할까?
//    @Autowired
//    private AccountContextService accountContextService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthorizationToken token = (PreAuthorizationToken)authentication;

        String userId = token.getUserId();
        String password = token.getPassword();

        Account account = accountRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("계정 정보를 찾을 수 없습니다."));
        if(isCorrectPassword(password, account)) {
            return PostAuthorizationToken.getFromAccountContext(AccountContext.fromAccount(account));
        }

        throw new NoSuchElementException("인증 정보를 찾을 수 없습니다.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //PreAuthorizationToken으로 들어오는 요청에 대해서 필터에 걸리게 된다.
        return PreAuthorizationToken.class.isAssignableFrom(authentication);
    }

    private boolean isCorrectPassword(String password, Account account) {
        return CommonUtils.getBCryptPasswordEncoder().matches(password, account.getPassword());
    }
}
