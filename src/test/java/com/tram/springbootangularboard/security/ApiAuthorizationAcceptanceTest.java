package com.tram.springbootangularboard.security;

import com.tram.springbootangularboard.config.properties.JwtProperties;
import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.dto.AccountDto;
import com.tram.springbootangularboard.dto.FormLoginDto;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableConfigurationProperties(JwtProperties.class)
public class ApiAuthorizationAcceptanceTest {
    @Autowired
    private TestRestTemplate template;

    public static final Logger log =  LoggerFactory.getLogger(ApiAuthorizationAcceptanceTest.class);

    @Test
    public void apiCallSuccessTest() throws JSONException {
        //회원가입 진행
        ResponseEntity<Account> accountResponse = template.postForEntity("/sign-up", AccountDto.Companion.defaultAccountDto(), Account.class);

        assertThat(accountResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(accountResponse.getHeaders().getLocation().getPath()).contains("/api/account");

        //로그인 후 토큰 값 얻음
        ResponseEntity<String> formLoginResponse = template.postForEntity("/login", new FormLoginDto(AccountDto.USER_ID, AccountDto.PASSWORD), String.class);

        assertThat(formLoginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(formLoginResponse.getBody()).isNotBlank();

        //얻어온 토큰값을 이용해 api 요청
        HttpHeaders headers = new HttpHeaders();
        JSONObject jsonObject = new JSONObject(formLoginResponse.getBody());
        headers.add("Authorization", "Bearer " + jsonObject.getString("token"));
        HttpEntity<AccountContext> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<AccountDto> accountInfoResponse = template.exchange("/api/accounts/current", HttpMethod.GET, httpEntity, AccountDto.class);

        assertThat(accountInfoResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(accountInfoResponse.getBody().getUsername()).isEqualTo(AccountDto.USERNAME);
    }

    @Test
    public void apiCallFailureTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + "not token");
        HttpEntity<AccountContext> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> accountInfoResponse = template.exchange("/api/accounts/current", HttpMethod.GET, httpEntity, String.class);

        //TODO Invalid Jwt Token Exception 발생 시 500이 아니라 403으로 반환해주고 싶은데..
        assertThat(accountInfoResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
