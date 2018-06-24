package com.tram.springbootangularboard.security;

import com.tram.springbootangularboard.config.properties.JwtProperties;
import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.domain.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JwtFactory.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
//ConfigrationProperties는 Test에서 따로 아래처럼 받아와야 사용 가능한 것 같다.
@EnableConfigurationProperties(JwtProperties.class)
public class JwtFactoryTest {
    @Autowired
    private JwtFactory jwtFactory;
    private final Logger log = LoggerFactory.getLogger(JwtFactoryTest.class);

    @Test
    public void generateJwtToken() {
        Account account = Account.builder().userId("tram").password("password")
                .userRole(Arrays.asList(UserRole.USER, UserRole.ADMIN)).username("규남").build();
        String jwtToken = jwtFactory.generateJwtToken(AccountContext.fromAccount(account));
        log.info("jwtToken value, {}", jwtToken);
        assertNotNull(jwtToken);
    }
}
