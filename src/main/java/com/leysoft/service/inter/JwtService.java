
package com.leysoft.service.inter;

import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.jsonwebtoken.Claims;

public interface JwtService {

    public String encode(Authentication authResult, String signature)
            throws JsonProcessingException;

    public Claims decode(String authorizationHeader, String signature);
}
