package com.tram.springbootangularboard;

import com.tram.springbootangularboard.config.properties.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class SpringBootAngularBoardApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAngularBoardApplication.class, args);
	}
}
