package com.tram.springbootangularboard.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tram.springbootangularboard.dto.AccountDto;
import com.tram.springbootangularboard.dto.FormLoginDto;
import com.tram.springbootangularboard.dto.PostSaveRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JpaAuditingTest {
    @Autowired
    private TestRestTemplate template;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public static final Logger log =  LoggerFactory.getLogger(JpaAuditingTest.class);

    @Test
    public void createdByAndCreateDateTest() throws IOException {
        //회원가입 진행
        ResponseEntity<Account> accountResponse = template.postForEntity("/sign-up",
                AccountDto.Companion.defaultAccountDto(), Account.class);

        assertThat(accountResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(accountResponse.getHeaders().getLocation().getPath()).contains("/api/account");

        //로그인 후 토큰 값 얻음
        ResponseEntity<String> formLoginResponse = template.postForEntity("/login", new FormLoginDto(AccountDto.EMAIL, AccountDto.PASSWORD), String.class);

        assertThat(formLoginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(formLoginResponse.getBody()).isNotBlank();

        //얻어온 토큰값을 이용해 api 요청
        HttpHeaders headers = new HttpHeaders();
        JsonNode jsonObj = objectMapper.readTree(formLoginResponse.getBody());

        //얻어온 토큰값을 담아서 post 생성 요청 전송, createdBy, createdDate 생성 검증
        String token = jsonObj.get("token").textValue();
        headers.add("Authorization", "Bearer " + token);
        PostSaveRequestDto postSaveRequestDto = new PostSaveRequestDto("hi", "hello", "hello");
        HttpEntity httpEntity = new HttpEntity(postSaveRequestDto, headers);
        ResponseEntity signUpResponse = template.exchange("/api/posts", HttpMethod.POST, httpEntity, String.class);

        assertThat(signUpResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(signUpResponse.getHeaders().getLocation().getPath()).contains("/api/posts");

        Post createdPost = postRepository.findById(Long.parseLong(signUpResponse.getHeaders()
                .getLocation().getPath().replace("/api/posts/", ""))).get();
        log.info("createdBy, {}", createdPost.getCreatedBy().getEmail());
        log.info("createdDateTime, {}", createdPost.getCreatedBy().getCreatedDate());
        assertThat(createdPost.getCreatedBy().getEmail()).isEqualTo(AccountDto.EMAIL);
        assertThat(createdPost.getCreatedDate()).isNotNull();
    }
}
