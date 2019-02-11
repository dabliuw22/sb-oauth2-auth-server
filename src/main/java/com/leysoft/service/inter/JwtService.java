
package com.leysoft.service.inter;

import java.util.Map;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.jsonwebtoken.Claims;

public interface JwtService {

    public String encode(Authentication authResult, String signature, long timeToLive)
            throws JsonProcessingException;

    public Claims decode(String authorizationHeader, String signature);

    public String body(Map<String, Object> params) throws JsonProcessingException;

    public String getUsername(Claims body);

    public Set<GrantedAuthority> getAuthorities(Claims body);
}
