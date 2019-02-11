
package com.leysoft.service.imple;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leysoft.service.inter.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImp implements JwtService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtServiceImp.class);

    private static final String PREFIX_BEARER = "Bearer ";

    private static final String AUTHORITIES_KEY = "authorities";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value(
            value = "${jwt.time-to-live}")
    private long timeToLive;

    @Override
    public String encode(Authentication authResult, String signature)
            throws JsonProcessingException {
        User user = (User) authResult.getPrincipal();
        java.util.List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        Claims claims = Jwts.claims();
        claims.put(AUTHORITIES_KEY, MAPPER.writeValueAsString(roles));
        Key key = Keys.hmacShaKeyFor(signature.getBytes());
        LOGGER.info("{} Algorithm", key.getAlgorithm());
        return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).signWith(key)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + this.timeToLive)).compact();
    }

    @Override
    public Claims decode(String authorizationHeader, String signature) {
        String jwt = authorizationHeader.replace(PREFIX_BEARER, "");
        return Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt).getBody();
    }
}
