package com.tram.springbootangularboard.security;

import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.dto.AccountDto;
import com.tram.springbootangularboard.dto.FormLoginDto;
import com.tram.springbootangularboard.support.AcceptanceTest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiAuthorizationAcceptanceTest extends AcceptanceTest {

    public static final Logger log =  LoggerFactory.getLogger(ApiAuthorizationAcceptanceTest.class);

    @Test
    public void apiCallSuccessTest() throws JSONException {
        //회원가입 진행
        ResponseEntity<Account> accountResponse = template().postForEntity("/sign-up", AccountDto.Companion.defaultAccountDto(), Account.class);

        assertThat(accountResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(accountResponse.getHeaders().getLocation().getPath()).contains("/api/account");

        //로그인 후 토큰 값 얻음
        ResponseEntity<String> formLoginResponse = template().postForEntity("/login", new FormLoginDto(AccountDto.EMAIL, AccountDto.PASSWORD), String.class);

        assertThat(formLoginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(formLoginResponse.getBody()).isNotBlank();

        //얻어온 토큰값을 이용해 api 요청
        HttpHeaders headers = new HttpHeaders();
        JSONObject jsonObject = new JSONObject(formLoginResponse.getBody());
        headers.add("Authorization", "Bearer " + jsonObject.getString("token"));
        log.info("token value {}", jsonObject.getString("token"));
        HttpEntity<AccountContext> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<AccountDto> accountInfoResponse = template().exchange("/api/accounts/current", HttpMethod.GET, httpEntity, AccountDto.class);

        assertThat(accountInfoResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(accountInfoResponse.getBody().getEmail()).isEqualTo(AccountDto.EMAIL);
    }

    @Test
    public void apiCallFailureTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + "not token");
        HttpEntity<AccountContext> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> accountInfoResponse = template().exchange("/api/accounts/current", HttpMethod.GET, httpEntity, String.class);

        //TODO Invalid Jwt Token Exception 발생 시 500이 아니라 403으로 반환해주고 싶은데..
        assertThat(accountInfoResponse.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
