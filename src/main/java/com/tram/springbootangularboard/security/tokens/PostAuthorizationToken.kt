package com.tram.springbootangularboard.security.tokens

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class PostAuthorizationToken : UsernamePasswordAuthenticationToken{
    constructor(principal: Any?, credentials: Any?, authorities: MutableCollection<out GrantedAuthority>?) : super(principal, credentials, authorities)
}
