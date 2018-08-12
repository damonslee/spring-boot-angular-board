package com.tram.springbootangularboard.controller;

import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.dto.AccountDto;
import com.tram.springbootangularboard.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/sign-up")
public class SignUpRestController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity create(@RequestBody AccountDto accountDto) {
        Account savedAccount = accountService.save(accountDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/accounts/" + savedAccount.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON_UTF8).headers(headers).build();
    }
}
