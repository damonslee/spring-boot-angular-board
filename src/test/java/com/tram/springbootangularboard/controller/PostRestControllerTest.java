package com.tram.springbootangularboard.controller;

import com.tram.springbootangularboard.common.CommonUtils;
import com.tram.springbootangularboard.domain.PostRepository;
import com.tram.springbootangularboard.dto.PostsSaveRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//secure false를 하지 않으면 Spring Security 디폴트 설정이 들어가서 권한 문제가 발생.
@WebMvcTest(value = PostRestController.class, secure = false)
public class PostRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostRepository postRepository;

    @Test
    public void read() throws Exception {
        this.mockMvc.perform(get("/api/posts").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void create() throws Exception {
        PostsSaveRequestDto param = new PostsSaveRequestDto();
        param.setTitle("제목");
        param.setContent("내용");
        param.setAuthor("tram");
        this.mockMvc.perform(post("/api/posts")
                .content(CommonUtils.getObjectMapper().writeValueAsBytes(param))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
}