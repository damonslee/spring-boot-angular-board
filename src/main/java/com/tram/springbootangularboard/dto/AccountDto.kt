package com.tram.springbootangularboard.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.tram.springbootangularboard.domain.Account

data class AccountDto(
        @field:JsonProperty("email")
        var email: String? = null,
        @field:JsonProperty("password")
        var password: String? = null,
        @field:JsonProperty("username")
        var username: String? = null
) {
    fun email(email: String): AccountDto {
        this.email = email
        return this
    }
    fun password(password: String): AccountDto {
        this.password = password
        return this
    }
    fun username(username: String): AccountDto {
        this.username = username
        return this
    }

    companion object {
        const val EMAIL: String = "xmfpes@naver.com"
        const val PASSWORD: String = "123123"
        const val USERNAME: String = "kyunam"

        fun defaultAccountDto(): AccountDto {
            return AccountDto(EMAIL, PASSWORD, USERNAME)
        }
    }

    fun toEntity(): Account {
        return Account(this.email, this.password, this.username)
    }
}