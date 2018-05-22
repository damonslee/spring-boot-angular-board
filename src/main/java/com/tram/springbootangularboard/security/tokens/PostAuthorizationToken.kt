package com.tram.springbootangularboard.security.tokens

import com.tram.springbootangularboard.domain.Account
import com.tram.springbootangularboard.security.AccountContext
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class PostAuthorizationToken : UsernamePasswordAuthenticationToken{
    constructor(principal: Any?, credentials: Any?, authorities: MutableCollection<out GrantedAuthority>?) : super(principal, credentials, authorities)
    companion object {
        fun getTokenFromAccountContext(accountContext: AccountContext): PostAuthorizationToken {
            return PostAuthorizationToken(accountContext, accountContext.password, accountContext.authorities)
        }
    }
}
