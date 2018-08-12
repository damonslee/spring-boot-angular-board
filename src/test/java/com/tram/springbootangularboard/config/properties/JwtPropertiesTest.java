package com.tram.springbootangularboard.config.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class JwtPropertiesTest {

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    public void secretKeyNotBlankTest() {
        assertThat(jwtProperties.getSecretKey()).isNotBlank();
    }
}