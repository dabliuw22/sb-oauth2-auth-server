
package com.leysoft.handler;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.leysoft.util.SecurityUtils;

public class DynamicAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private RedirectStrategy redirectStrategy;

    private String defaultUrl;

    public DynamicAuthenticationFailureHandler(String defaultUrl) {
        this.defaultUrl = defaultUrl;
        this.redirectStrategy = new DefaultRedirectStrategy();
    }

    public DynamicAuthenticationFailureHandler(RedirectStrategy redirectStrategy,
            String defaultUrl) {
        this.redirectStrategy = redirectStrategy;
        this.defaultUrl = defaultUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String params = (String) SecurityUtils.Session.get(session,
                SecurityUtils.Session.PARAMETERS_FAILURE_LOGIN_NAME);
        SecurityUtils.Session.remove(session, SecurityUtils.Session.REDIRECT_LOGIN_URI_NAME);
        SecurityUtils.Session.remove(session, SecurityUtils.Session.PARAMETERS_FAILURE_LOGIN_NAME);
        String url = Objects.nonNull(params) ? defaultUrl + "&" + params : defaultUrl;
        redirectStrategy.sendRedirect(request, response, url);
    }
}
