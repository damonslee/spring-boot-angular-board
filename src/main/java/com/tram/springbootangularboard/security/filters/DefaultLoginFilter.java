package com.tram.springbootangularboard.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tram.springbootangularboard.dto.DefaultLoginDto;
import com.tram.springbootangularboard.security.tokens.PreAuthorizationToken;
import com.tram.springbootangularboard.util.ObjectMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultLoginFilter extends AbstractAuthenticationProcessingFilter {
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;

    public DefaultLoginFilter(String defaultUrl, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super(defaultUrl);
        this.authenticationSuccessHandler = successHandler;
        this.authenticationFailureHandler = failureHandler;
    }
    protected DefaultLoginFilter(String defaultFilterProcessesUrl){
        super(defaultFilterProcessesUrl);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        DefaultLoginDto dto = new ObjectMapper().readValue(request.getReader(), DefaultLoginDto.class);
        PreAuthorizationToken token = new PreAuthorizationToken(dto.getId(), dto.getPassword());
        return super.getAuthenticationManager().authenticate(token);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        AuthenticationFailureHandler authenticationFailureHandler = (req, res, exception) -> {
            Logger log = LoggerFactory.getLogger("authentication failure");
            log.error("login fail");
        };
        authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
    }
}
