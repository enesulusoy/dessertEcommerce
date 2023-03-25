package com.euce.dessert.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtProvider {
    @Value("${security.jwt.uri:/login}")
    private String uri;
    @Value("${security.jwt.header:Authorization}")
    private String header;
    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;
    @Value("${security.jwt.expiration:#{8*60*60}}")
    private int expiration;
    @Value("${security.jwt.secret:JWTSecretKey}")
    private String secret;

    public String getUri() {
        return uri;
    }

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }
}
