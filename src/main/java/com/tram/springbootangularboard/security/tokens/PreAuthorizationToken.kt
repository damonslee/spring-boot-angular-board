package com.tram.springbootangularboard.security.tokens

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class PreAuthorizationToken : UsernamePasswordAuthenticationToken{
    constructor(principal: kotlin.Any, credentials: kotlin.Any): super(principal, credentials)
    constructor(userId: String, password: String): super(userId, password)
    fun getUserId(): String {
        return super.getPrincipal() as String
    }
    fun getUserPassword(): String {
        return super.getCredentials() as String
    }
}