package com.tram.springbootangularboard.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DefaultLoginDto(
        @field:JsonProperty("userId")
        var id: String? = null,
        @field:JsonProperty("password")
        var password: String? = null
)