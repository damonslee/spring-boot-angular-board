package com.tram.springbootangularboard.security.config;

import com.tram.springbootangularboard.security.filters.DefaultLoginFilter;
import com.tram.springbootangularboard.security.handlers.DefaultAuthenticationSuccessHandler;
import com.tram.springbootangularboard.security.providers.DefaultLoginAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;
    @Autowired
    private DefaultLoginAuthenticationProvider defaultLoginAuthenticationProvider;
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    protected DefaultLoginFilter defaultLoginFilter() throws Exception {
        DefaultLoginFilter filter = new DefaultLoginFilter("/formlogin", defaultAuthenticationSuccessHandler, null);
        filter.setAuthenticationManager(super.authenticationManagerBean());

        return filter;
    }
    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(this.defaultLoginAuthenticationProvider);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();

        http
                .addFilterBefore(defaultLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
