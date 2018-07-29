package com.tram.springbootangularboard.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tram.springbootangularboard.config.properties.JwtProperties;
import com.tram.springbootangularboard.domain.UserRole;
import com.tram.springbootangularboard.exception.InvalidJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtDecoder {
    private static final Logger log = LoggerFactory.getLogger(JwtDecoder.class);

    @Autowired
    private JwtProperties jwtProperties;

    public AccountContext decodeToken(String token) {
        //TODO 토큰이 유효하지 않은 경우 500에러가 발생하는데 이 부분이 수정되어야 할까?
        DecodedJWT decodedJWT = isValidToken(token).orElseThrow(() -> new InvalidJwtException("유효한 토큰이 아닙니다."));
        String username = decodedJWT.getClaim("USERNAME").asString();
        List<SimpleGrantedAuthority> authorities = decodedJWT.getClaim("AUTHORITIES")
                .asList(UserRole.class).stream().map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName())).collect(Collectors.toList());
        return new AccountContext(username, "0000", authorities);
    }

    private Optional<DecodedJWT> isValidToken(String token) {
        DecodedJWT decodedJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtProperties.getSecretKey());
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            decodedJWT = jwtVerifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return Optional.ofNullable(decodedJWT);
    }
}
