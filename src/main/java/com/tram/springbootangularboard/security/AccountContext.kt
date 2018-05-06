package com.tram.springbootangularboard.security

import com.tram.springbootangularboard.domain.Account
import com.tram.springbootangularboard.enum.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.*
import java.util.stream.Collectors
import kotlin.streams.toList

class AccountContext : User {
    private constructor(username: String?, password: String?, authorities: MutableCollection<out GrantedAuthority>?) : super(username, password, authorities)
    companion object {
        fun fromAccountModel(account: Account) : AccountContext {
            return AccountContext(account.userId, account.password, parseAuthorities(account.userRole))
        }
        fun parseAuthorities(userRole: UserRole?): MutableList<SimpleGrantedAuthority>? {
            return Arrays.asList(userRole).stream().map { SimpleGrantedAuthority(it?.roleName) }.collect(Collectors.toList())
        }
    }
}