
package com.leysoft.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.leysoft.entity.CustomRole;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getCurrentUsername() {
        return getCurrentAuthentication().getName();
    }

    public static boolean isAuthenticated() {
        return !(getCurrentAuthentication() instanceof AnonymousAuthenticationToken);
    }

    public static Set<GrantedAuthority> toAuthorities(Set<CustomRole> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
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

    public static class Name {

        public static final String USERNAME_NAME = "username";

        public static final String PASW_NAME = "password";

        public static final String PROTOCOL_NAME = "protocol";

        public static final String CLIENT_ID_NAME = "client_id";

        public static final String REDIRECT_URI_NAME = "redirect_uri";

        public static final String RESPONSE_TYPE_NAME = "response_type";

        public static final String SCOPE_NAME = "scope";

        private Name() {
        }
    }

    public static class Oauth2 {

        public static final String PROTOCOL = "oauth2";

        public static final String OAUTH_AUTHORIZE_ENDPOINT = "/oauth/authorize";

        private Oauth2() {
        }

        public static String buildUrl(String clientId, String redirectUri, String responseType,
                String scope) {
            return OAUTH_AUTHORIZE_ENDPOINT + "?" + Name.CLIENT_ID_NAME + "=" + clientId + "&"
                    + Name.REDIRECT_URI_NAME + "=" + redirectUri + "&" + Name.RESPONSE_TYPE_NAME
                    + "=" + responseType + "&" + Name.SCOPE_NAME + "=" + scope;
        }

        public static String buildParameters(String protocol, String clientId, String redirectUri,
                String responseType, String scope) {
            return Name.PROTOCOL_NAME + "=" + protocol + "&" + Name.CLIENT_ID_NAME + "=" + clientId
                    + "&" + Name.REDIRECT_URI_NAME + "=" + redirectUri + "&"
                    + Name.RESPONSE_TYPE_NAME + "=" + responseType + "&" + Name.SCOPE_NAME + "="
                    + scope;
        }
    }

    public static class Session {

        public static final String REDIRECT_LOGIN_URI_NAME = "redirect_login_uri";

        public static final String PARAMETERS_FAILURE_LOGIN_NAME = "parameters_login";

        private Session() {
        }

        public static void loginOperation(String protocol, String clientId, String redirectUri,
                String responseType, String scope, HttpSession session) {
            remove(session, REDIRECT_LOGIN_URI_NAME);
            remove(session, PARAMETERS_FAILURE_LOGIN_NAME);
            if (Objects.nonNull(protocol) && protocol.equals(Oauth2.PROTOCOL)
                    && Objects.nonNull(clientId) && Objects.nonNull(redirectUri)
                    && Objects.nonNull(responseType) && Objects.nonNull(scope)) {
                String url = Oauth2.buildUrl(clientId, redirectUri, responseType, scope);
                String params = Oauth2.buildParameters(protocol, clientId, redirectUri,
                        responseType, scope);
                add(session, REDIRECT_LOGIN_URI_NAME, url);
                add(session, PARAMETERS_FAILURE_LOGIN_NAME, params);
            }
        }

        public static String redirectOperation(String protocol, String clientId, String redirectUri,
                String responseType, String scope) {
            if (Objects.nonNull(protocol) && protocol.equals(Oauth2.PROTOCOL)
                    && Objects.nonNull(clientId) && Objects.nonNull(redirectUri)
                    && Objects.nonNull(responseType) && Objects.nonNull(scope)) {
                return "redirect:" + Oauth2.buildUrl(clientId, redirectUri, responseType, scope);
            } else {
                return "redirect:/";
            }
        }

        public static <T extends Serializable> void add(HttpSession session, String name, T value) {
            session.setAttribute(name, value);
        }

        public static void remove(HttpSession session, String name) {
            session.removeAttribute(name);
        }

        public static Object get(HttpSession session, String name) {
            return session.getAttribute(name);
        }
    }
}
