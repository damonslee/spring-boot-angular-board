package com.tram.springbootangularboard.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.tram.springbootangularboard.domain.Account

data class AccountDto(
        @field:JsonProperty("userId")
        var userId: String? = null,
        @field:JsonProperty("password")
        var password: String? = null,
        @field:JsonProperty("username")
        var username: String? = null
) {
    fun userId(userId: String): AccountDto {
        this.userId = userId
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
        const val USER_ID: String = "xmfpes@naver.com"
        const val PASSWORD: String = "123123"
        const val USERNAME: String = "kyunam"

        fun defaultAccountDto(): AccountDto {
            return AccountDto(USER_ID, PASSWORD, USERNAME)
        }
    }

    fun toEntity(): Account {
        return Account(this.userId, this.password, this.username)
    }
}