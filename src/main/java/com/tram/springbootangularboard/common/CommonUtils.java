package com.tram.springbootangularboard.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
