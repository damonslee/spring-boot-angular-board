package com.tram.springbootangularboard.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.tram.springbootangularboard.dto.DefaultLoginDto
import java.io.BufferedReader

class ObjectMapperUtil {
    companion object {
        private val objectMapper: ObjectMapper = ObjectMapper()
        fun writeValueAsString(value: Object): String {
            return objectMapper.writeValueAsString(value)
        }
        fun readValue(reader: BufferedReader, clazz: Class<DefaultLoginDto>): Any? {
            return objectMapper.readValue(reader, clazz)
        }
    }
}