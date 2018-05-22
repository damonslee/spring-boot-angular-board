package com.tram.springbootangularboard.security

import com.tram.springbootangularboard.domain.AccountRepository
import com.tram.springbootangularboard.exception.AccountNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class AccountContextService(@Autowired private val accountRepository: AccountRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        return AccountContext.fromAccountModel(accountRepository.findByUserId(username)!!.orElseThrow { AccountNotFoundException("유저 정보를 찾을 수 없습니다.") })
    }
}