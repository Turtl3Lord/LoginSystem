package com.BackEnd.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZonedDateTime;

@Component
public class JwtTokenProvider {

    private final JwtConfig jwtConfig;

    public JwtTokenProvider(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;

    }

    public String generateAccessToken(String userId) {
            Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecretKey());
            return JWT.create()
                    .withIssuer(jwtConfig.getIssuer())
                    .withIssuedAt(creationDate())
                    .withExpiresAt(accessTokenExpirationDate())
                    .withSubject(userId)
                    .withClaim("type", "access")
                    .sign(algorithm);
    }

    private Instant creationDate() {
        return ZonedDateTime.now().toInstant();
    }

    private Instant accessTokenExpirationDate() {
        return ZonedDateTime.now()
                .plusSeconds(jwtConfig.getAccessTokenExpiration())
                .toInstant();
    }


}