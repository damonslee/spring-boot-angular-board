package com.tram.springbootangularboard.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tram.springbootangularboard.config.properties.JwtProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JwtFactory {
    @Autowired
    private JwtProperties jwtProperties;
    private final Logger log = LoggerFactory.getLogger(JwtFactory.class);

    public String generateJwtToken(AccountContext accountContext) {
        String jwtToken = null;
        try {
            jwtToken = JWT.create().withIssuer("tram")
                    .withClaim("USERNAME", accountContext.getAccount().getUsername())
                    .withClaim("USER_ID", accountContext.getAccount().getUserId())
                    .withArrayClaim("AUTHORITIES", accountContext.getAuthorities().stream()
                            .map(r -> r.getAuthority()).toArray(String[]::new))
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            //TODO custom exception 만들어서 처리하기.
            log.error(e.getMessage());
        }
        return jwtToken;
    }

    private Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(jwtProperties.getSecretKey());
    }
}
