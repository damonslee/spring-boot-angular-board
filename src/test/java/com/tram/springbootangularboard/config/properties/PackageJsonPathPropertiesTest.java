package com.tram.springbootangularboard.config.properties;

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
public class PackageJsonPathPropertiesTest {
    @Autowired
    private PackageJsonPathProperties packageJsonPathProperties;

    @Test
    public void packageJsonPathNotBlankTest() {
        assertThat(packageJsonPathProperties.getPath()).isNotBlank();
    }
}