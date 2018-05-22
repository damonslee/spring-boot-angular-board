package com.tram.springbootangularboard.security.providers

import com.tram.springbootangularboard.domain.Account
import com.tram.springbootangularboard.domain.AccountRepository
import com.tram.springbootangularboard.exception.AccountNotFoundException
import com.tram.springbootangularboard.security.AccountContext
import com.tram.springbootangularboard.security.AccountContextService
import com.tram.springbootangularboard.security.tokens.PostAuthorizationToken
import com.tram.springbootangularboard.security.tokens.PreAuthorizationToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class DefaultLoginAuthenticationProvider(
        @Autowired private val accountRepository: AccountRepository,
        @Autowired private val passwordEncoder: PasswordEncoder

) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication? {
        var token: PreAuthorizationToken = authentication as PreAuthorizationToken
        val userId: String = token.getUserId()
        val password: String? = token.getUserPassword()
        val account: Account = accountRepository.findByUserId(userId)!!.orElseThrow { AccountNotFoundException("유저 정보를 찾을 수 없습니다.") }
        if(isCorrectPassword(password, account)) {
            return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(account))
        }
        throw AccountNotFoundException("계정 정보가 이상해요")
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return PreAuthorizationToken::class.java.isAssignableFrom(authentication)
    }

    fun isCorrectPassword(password: String?, account: Account): Boolean {
        return passwordEncoder.matches(password, account.password)
    }
}