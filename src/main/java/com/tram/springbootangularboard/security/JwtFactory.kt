package com.tram.springbootangularboard.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Component
import java.io.UnsupportedEncodingException

@Component
class JwtFactory(
        val signingKey: String = "jwttest"
){
    fun generateToken(context: AccountContext): String {
        var token: String? = null;
        try {
            token = JWT.create()
                    .withIssuer("tram")
                    .withClaim("USER_ROLE", context.authorities.first().toString() as String?)
                    .sign(generateAlgorithm())
        } catch(e: Exception){
            e.printStackTrace()
        }
        return token.toString()
    }
    @Throws(UnsupportedEncodingException::class)
    private fun generateAlgorithm(): Algorithm {
        return Algorithm.HMAC256(signingKey)
    }
}