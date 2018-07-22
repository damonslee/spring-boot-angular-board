package com.tram.springbootangularboard.security.filter;

import com.tram.springbootangularboard.security.HeaderTokenExtractor;
import com.tram.springbootangularboard.security.handler.JwtAuthenticationFailureHandler;
import com.tram.springbootangularboard.security.token.JwtPreProcessingToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;
    private HeaderTokenExtractor headerTokenExtractor;
    protected JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }
    public JwtAuthenticationFilter(RequestMatcher requestMatcher, JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler, HeaderTokenExtractor headerTokenExtractor) {
        super(requestMatcher);
        this.jwtAuthenticationFailureHandler = jwtAuthenticationFailureHandler;
        this.headerTokenExtractor = headerTokenExtractor;
    }

    //요청이 처음 들어왔을 때 이 메소드를 통해 Provider에 인증 전 객체가 전달이 되게 된다.
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader("Authorization");
        JwtPreProcessingToken token = new JwtPreProcessingToken(this.headerTokenExtractor.extract(tokenPayload));
        return super.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authResult);
        SecurityContextHolder.setContext(securityContext);

        chain.doFilter(request, response);

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //토큰을 주고받는 과정에서 유효하지 않은 jwt 토큰을 만났으므로 초기화해준다.
        SecurityContextHolder.clearContext();
        this.jwtAuthenticationFailureHandler.onAuthenticationFailure(request, response, failed);
    }
}
