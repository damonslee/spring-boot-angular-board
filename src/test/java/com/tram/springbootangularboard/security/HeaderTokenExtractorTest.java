package com.tram.springbootangularboard.security;

import com.tram.springbootangularboard.exception.InvalidJwtException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
//웹 환경이 필요없는 테스트 SpringBootTest.WebEnvironment.NONE 부여
@SpringBootTest(classes = HeaderTokenExtractor.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HeaderTokenExtractorTest {

    @Autowired
    private HeaderTokenExtractor headerTokenExtractor;

    @Test
    public void extract() {
        assertThat(headerTokenExtractor.extract("Bearer tokentest")).isEqualTo("tokentest");
    }

    @Test(expected = NullPointerException.class)
    public void extractNullTokenValueTest() {
        headerTokenExtractor.extract(null);
    }

    @Test(expected = InvalidJwtException.class)
    public void extractShortTokenValueTest() {
        headerTokenExtractor.extract("1234");
    }
}
