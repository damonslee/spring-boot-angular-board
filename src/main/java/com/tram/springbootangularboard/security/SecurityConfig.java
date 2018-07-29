package com.tram.springbootangularboard.security;

import com.tram.springbootangularboard.security.filter.FormLoginFilter;
import com.tram.springbootangularboard.security.filter.JwtAuthenticationFilter;
import com.tram.springbootangularboard.security.handler.FormLoginAuthenticationSuccessHandler;
import com.tram.springbootangularboard.security.handler.JwtAuthenticationFailureHandler;
import com.tram.springbootangularboard.security.provider.FormLoginAuthenticationProvider;
import com.tram.springbootangularboard.security.provider.JwtAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

//아래 두 어노테이션은 기본 default 보안 설정을 초기화한다. actuator 보안은 그대로 유지.
@Configuration
@EnableWebSecurity
//메소드 실행 전 후 condition을 통해 체크 후 진입 가능하도록 하는 설정
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private FormLoginAuthenticationProvider formLoginAuthenticationProvider;
    @Autowired
    private FormLoginAuthenticationSuccessHandler formLoginAuthenticationSuccessHandler;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private HeaderTokenExtractor headerTokenExtractor;
    @Autowired
    private JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

    protected FormLoginFilter formLoginFilter() throws Exception {
        FormLoginFilter filter = new FormLoginFilter("/login", formLoginAuthenticationSuccessHandler, (req, res, exception) -> {
            Logger logger = LoggerFactory.getLogger(FormLoginFilter.class);
            logger.error(exception.getMessage());
        });
        filter.setAuthenticationManager(super.authenticationManagerBean());
        return filter;
    }

    protected JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        //회원가입과 로그인의 경우 Jwt 인증을 패스한다.
        FilterSkipMatcher filterSkipMatcher = new FilterSkipMatcher(Arrays.asList("/login", "/sign-up"), "/api/**");
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(filterSkipMatcher, jwtAuthenticationFailureHandler, headerTokenExtractor);
        jwtAuthenticationFilter.setAuthenticationManager(super.authenticationManagerBean());
        return jwtAuthenticationFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //FormLoginAuthenticationProvider 등록
        auth.authenticationProvider(this.formLoginAuthenticationProvider);
        //JwtAuthenticationProvider 등록
        auth.authenticationProvider(this.jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //JWT Token 사용 시 csrf 방어를 켤 필요가 없다.
        http.csrf().disable();
        //X-Frame-Options disable iframe을 사용하지 않을 것 같지만.. disable
        http.headers().frameOptions().disable();
        //UsernamePasswordAuthenticationFilter 앞에 FormLoginFilter 추가
        http.addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
