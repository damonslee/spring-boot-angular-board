package com.tram.springbootangularboard.security;

import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class AccountContextService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account account =  accountRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("해당하는 계정을 찾을 수 없습니다."));
        return getAccountContext(account);
    }

    public UserDetails loadAccountByUserId(String userId) {
        return loadUserByUsername(userId);
    }

    private AccountContext getAccountContext(Account account) {
        return AccountContext.fromAccount(account);
    }
}
