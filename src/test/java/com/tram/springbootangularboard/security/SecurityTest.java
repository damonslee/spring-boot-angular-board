package com.tram.springbootangularboard.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tram.springbootangularboard.common.CommonConfiguration;
import com.tram.springbootangularboard.config.properties.JwtProperties;
import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.domain.AccountRepository;
import com.tram.springbootangularboard.domain.UserRole;
import com.tram.springbootangularboard.dto.FormLoginDto;
import com.tram.springbootangularboard.security.handler.FormLoginAuthenticationSuccessHandler;
import com.tram.springbootangularboard.security.handler.JwtAuthenticationFailureHandler;
import com.tram.springbootangularboard.security.provider.FormLoginAuthenticationProvider;
import com.tram.springbootangularboard.security.provider.JwtAuthenticationProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void loginSuccessTest() throws Exception {
        given(accountRepository.findByUserId("xmfpes")).willReturn(Optional.ofNullable(Account.builder()
                .userId("xmfpes").password(bCryptPasswordEncoder.encode("1234")).username("kyuNam").userRole(Arrays.asList(UserRole.ROLE_USER)).build()));
        this.mockMvc.perform(post("/login")
                .content(objectMapper.writeValueAsBytes(new FormLoginDto("xmfpes", "1234")))
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test(expected = NoSuchElementException.class)
    public void loginFailureTest() throws Exception {
        given(accountRepository.findByUserId("xmfpes")).willReturn(Optional.ofNullable(Account.builder()
                .userId("xmfpes").password(bCryptPasswordEncoder.encode("1234")).userRole(Arrays.asList(UserRole.ROLE_USER)).build()));
        this.mockMvc.perform(post("/login")
                .content(objectMapper.writeValueAsBytes(new FormLoginDto("xmfpes", "5555")))
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
