package com.tram.springbootangularboard.security;
import com.tram.springbootangularboard.config.properties.JwtProperties;
import com.tram.springbootangularboard.dto.AccountDto;
import com.tram.springbootangularboard.dto.FormLoginDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityLoginAcceptanceTest {
    @Autowired
    private TestRestTemplate template;

    public static final Logger log =  LoggerFactory.getLogger(SecurityLoginAcceptanceTest.class);
    @Test
    public void loginSuccessTest() {
        ResponseEntity accountResponse = template.postForEntity("/sign-up", AccountDto.Companion.defaultAccountDto(), String.class);

        assertThat(accountResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(accountResponse.getHeaders().getLocation().getPath()).contains("/api/account");

        ResponseEntity<String> formLoginResponse = template.postForEntity("/login", new FormLoginDto(AccountDto.EMAIL, AccountDto.PASSWORD), String.class);

        assertThat(formLoginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(formLoginResponse.getBody()).isNotBlank();
        log.info("jwt token value, {}", formLoginResponse.getBody());
    }
}
