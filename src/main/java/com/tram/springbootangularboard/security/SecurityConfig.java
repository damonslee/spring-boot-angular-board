package com.tram.springbootangularboard.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//아래 두 어노테이션은 기본 default 보안 설정을 초기화한다. actuator 보안은 그대로 유지.
@Configuration
@EnableWebSecurity
//메소드 실행 전 후 condition을 통해 체크 후 진입 가능하도록 하는 설정
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
}
