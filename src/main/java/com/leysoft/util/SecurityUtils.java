
package com.leysoft.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.leysoft.entity.CustomRole;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static Set<GrantedAuthority> toAuthorities(Set<CustomRole> roles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    public static Set<String> toStrings(Collection<GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    public static PasswordEncoder getBCryptPasswordEncoderInstance() {
        return new BCryptPasswordEncoder();
    }

    public static PasswordEncoder getNoopPasswordEncoderInstance() {
        return new PasswordEncoder() {
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }

            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }
        };
    }

    public static class Header {

        public static final String HEADER_NAME = "Authorization";

        public static final String PREFIX_BEARER = "Bearer ";

        private Header() {
        }

        public static String replaceBearerHeader(String bearer) {
            return bearer.replace(PREFIX_BEARER, "");
        }
    }

    public static class Name {

        public static final String USERNAME_NAME = "username";

        public static final String PASW_NAME = "password";

        private Name() {
        }
    }
}
