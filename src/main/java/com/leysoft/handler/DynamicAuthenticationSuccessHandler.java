
package com.leysoft.handler;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.leysoft.util.SecurityUtils;

public class DynamicAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy;

    private String defaultUrl;

    public DynamicAuthenticationSuccessHandler(String defaultUrl) {
        this.defaultUrl = defaultUrl;
        this.redirectStrategy = new DefaultRedirectStrategy();
    }

    public DynamicAuthenticationSuccessHandler(RedirectStrategy redirectStrategy,
            String defaultUrl) {
        this.redirectStrategy = redirectStrategy;
        this.defaultUrl = defaultUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String url = (String) SecurityUtils.Session.get(session,
                SecurityUtils.Session.REDIRECT_LOGIN_URI_NAME);
        SecurityUtils.Session.remove(session, SecurityUtils.Session.REDIRECT_LOGIN_URI_NAME);
        SecurityUtils.Session.remove(session, SecurityUtils.Session.PARAMETERS_FAILURE_LOGIN_NAME);
        url = Objects.nonNull(url) ? url : defaultUrl;
        redirectStrategy.sendRedirect(request, response, url);
    }
}
