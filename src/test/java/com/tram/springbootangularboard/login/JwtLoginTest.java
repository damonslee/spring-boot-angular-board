package com.tram.springbootangularboard.login;

import com.tram.springbootangularboard.domain.Account;
import com.tram.springbootangularboard.dto.DefaultLoginDto;
import com.tram.springbootangularboard.util.ObjectMapperUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JwtLoginTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void login() throws Exception {
        DefaultLoginDto defaultLoginDto = new DefaultLoginDto();
        defaultLoginDto.setId("kyunam");
        defaultLoginDto.setPassword("디지몬");
        this.mockMvc.perform(get("/formlogin").accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapperUtil.Companion.writeValueAsString(defaultLoginDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
