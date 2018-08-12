package com.tram.springbootangularboard.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("local")
public class WebpackWatchTest {
    @Autowired
    private WebpackWatchExecutor webpackWatchExecutor;

    @Test
    public void webpackWatchExecutorBeanCreationTest() {
        assertThat(this.webpackWatchExecutor).isNotNull();
    }
}
