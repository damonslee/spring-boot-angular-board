package com.tram.springbootangularboard.controller;


import com.tram.springbootangularboard.dto.AccountDto;
import com.tram.springbootangularboard.security.token.PostAuthorizationToken;
import com.tram.springbootangularboard.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountRestController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/current")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity readCurrentAccount(Authentication authentication) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail(token.getAccountContext().getAccount().getEmail());
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(accountDto);
    }
}
