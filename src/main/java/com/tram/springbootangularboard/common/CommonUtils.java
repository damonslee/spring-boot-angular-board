package com.tram.springbootangularboard.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CommonUtils {
    public static BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
