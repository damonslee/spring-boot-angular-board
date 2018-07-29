package com.tram.springbootangularboard.security;

import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.domain.AccountRepository;
import com.tram.springbootangularboard.domain.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
//웹 환경이 필요없는 테스트 SpringBootTest.WebEnvironment.NONE 부여
@SpringBootTest(classes = AccountContextService.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AccountContextServiceTest {
   @MockBean
   private AccountRepository accountRepository;

   @Autowired
   private AccountContextService accountContextService;

    /**
     * MockBean을 이용해 AccountContext 가져오는 여부 테스트
     */
    @Test
    public void loadAccountByUserId() {
        given(accountRepository.findByEmail("testId")).willReturn(Optional.ofNullable(Account.builder()
                .email("testId").password("password").userRole(Arrays.asList(UserRole.ROLE_USER)).build()));
        assertNotNull(accountContextService.loadAccountByUserId("testId"));
    }

    /**
     * 계정 정보가 존재하지 않는 경우 Exception 테스트
     */
    //TODO Exception 생성 이후 변경 필요
    @Test(expected = NoSuchElementException.class)
    public void loadAccountByUserIdExceptionTest() {
        given(accountRepository.findByEmail("testId")).willReturn(Optional.ofNullable(Account.builder()
                .email("testId").password("password").userRole(Arrays.asList(UserRole.ROLE_USER)).build()));
        accountContextService.loadAccountByUserId("noId");
    }


}
