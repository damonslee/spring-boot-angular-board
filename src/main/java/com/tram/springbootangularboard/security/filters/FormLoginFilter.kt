package com.tram.springbootangularboard.security.filters

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class FormLoginFilter : AbstractAuthenticationProcessingFilter {
    constructor(defaultFilterProcessesUrl: String) : super(defaultFilterProcessesUrl)
    constructor(defaultFilterProcessesUrl: String, authenticationSuccessHandler: AuthenticationSuccessHandler, authenticationFailureHandler: AuthenticationFailureHandler) : this(defaultFilterProcessesUrl) {
        this.setAuthenticationSuccessHandler(authenticationSuccessHandler)
        this.setAuthenticationFailureHandler(authenticationFailureHandler)
    }
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}