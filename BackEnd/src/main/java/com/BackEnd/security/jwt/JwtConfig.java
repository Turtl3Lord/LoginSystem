package com.BackEnd.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    @Value("${JWT_SECRET_KEY}")
    private String secretKey;

    @Value("${JWT_EXPIRATION_SECONDS}")
    private int accessTokenExpiration;


    @Value("${JWT_ISSUER}")
    private String issuer;

    public String getSecretKey() {
        return secretKey;
    }

    public int getAccessTokenExpiration() {
        return accessTokenExpiration;
    }


    public String getIssuer() {
        return issuer;
    }
}