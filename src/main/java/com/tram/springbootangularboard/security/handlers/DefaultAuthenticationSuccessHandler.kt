package com.tram.springbootangularboard.security.handlers

import com.tram.springbootangularboard.dto.TokenDto
import com.tram.springbootangularboard.security.AccountContext
import com.tram.springbootangularboard.security.JwtFactory
import com.tram.springbootangularboard.security.tokens.PostAuthorizationToken
import com.tram.springbootangularboard.util.ObjectMapperUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse



@Component
class DefaultAuthenticationSuccessHandler(
        @Autowired private val jwtFactory: JwtFactory
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication?) {
        val postAuthorizationToken: PostAuthorizationToken = authentication as PostAuthorizationToken
        val accountContext: AccountContext = postAuthorizationToken.principal as AccountContext
        val tokenString: String = jwtFactory.generateToken(accountContext)
        setResponse(response, writeTokenDto(tokenString))
    }
    private fun writeTokenDto(token: String): TokenDto {
        return TokenDto(token)
    }
    private fun setResponse(response: HttpServletResponse, tokenDto: TokenDto) {

        response.contentType = MediaType.APPLICATION_JSON_UTF8_VALUE
        response.status = HttpStatus.OK.value()
        response.writer.write(ObjectMapperUtil.writeValueAsString(tokenDto as Object))
    }
}