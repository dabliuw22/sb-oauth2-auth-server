
package com.leysoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leysoft.entity.CustomUser;
import com.leysoft.service.inter.CustomUserService;
import com.leysoft.service.inter.JwtService;

@RestController
@RequestMapping(
        value = {
            "/user"
        })
public class UserController {

    @Value(
            value = "${jwt.signature}")
    private String signatureKey;

    @Autowired
    private CustomUserService customUserService;

    @Autowired
    private JwtService jwtService;

    @PreAuthorize(
            value = "hasRole('ADMIN')")
    @GetMapping(
            value = {
                "/{id}"
            })
    public ResponseEntity<CustomUser> getUserById(@PathVariable(
            value = "id") Long id) {
        return ResponseEntity.ok(customUserService.getUserById(id));
    }

    @PreAuthorize(
            value = "hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<CustomUser> getUser(@RequestHeader(
            value = "Authorization",
            required = true) String authorizationHeader) {
        jwtService.decode(authorizationHeader, signatureKey);
        return ResponseEntity.ok(customUserService.getUser());
    }
}
