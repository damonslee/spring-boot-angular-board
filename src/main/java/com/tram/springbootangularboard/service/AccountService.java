package com.tram.springbootangularboard.service;

import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.domain.AccountRepository;
import com.tram.springbootangularboard.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Account save(AccountDto accountDto) {
        Account account = Account.fromDto(accountDto, bCryptPasswordEncoder);
        return accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public Account findByUserId(String userId) {
        return accountRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("계정 정보를 찾을 수 없습니다."));
    }
}
