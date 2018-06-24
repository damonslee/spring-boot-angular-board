package com.tram.springbootangularboard.security.handler;

import com.tram.springbootangularboard.common.CommonUtils;
import com.tram.springbootangularboard.dto.TokenDto;
import com.tram.springbootangularboard.security.AccountContext;
import com.tram.springbootangularboard.security.JwtFactory;
import com.tram.springbootangularboard.security.token.PostAuthorizationToekn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증 성공 시 토큰 값을 받아서 response로 내려주는 역할.
 */
@Component
public class FormLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtFactory jwtFactory;

    /**
     * 인증 성공 시 jwt Token을 Response에 담아 반환한다.
     * @param request
     * @param response
     * @param authentication Provider에서 authenticate 메소드를 통해 내려준 Authentication(PostAuthorizationToken)
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        PostAuthorizationToekn token = (PostAuthorizationToekn)authentication;
        AccountContext context = (AccountContext)token.getPrincipal();
        String jwtToken = jwtFactory.generateJwtToken(context);
        processResponse(response, writeTokenDto(jwtToken));
    }

    private TokenDto writeTokenDto(String jwtToken) {
        return new TokenDto(jwtToken);
    }

    private void processResponse(HttpServletResponse response, TokenDto tokenDto) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(CommonUtils.getObjectMapper().writeValueAsString(tokenDto));
    }
}