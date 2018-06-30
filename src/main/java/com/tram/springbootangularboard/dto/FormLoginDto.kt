package com.tram.springbootangularboard.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class FormLoginDto (
        @field:JsonProperty("userId")
        val userId: String? = null,
        @field:JsonProperty("password")
        val password: String? = null
)