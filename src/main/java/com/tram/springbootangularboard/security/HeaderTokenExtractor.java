package com.tram.springbootangularboard.security;

import com.tram.springbootangularboard.exception.InvalidJwtException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class HeaderTokenExtractor {
    private static final String HEADER_PREFIX = "Bearer ";
    public String extract(String header) {
        if(StringUtils.isEmpty(header) || header.length() < HEADER_PREFIX.length()) {
            throw new InvalidJwtException("토큰 정보가 잘못되었습니다.");
        }
        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}