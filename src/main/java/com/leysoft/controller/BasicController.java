
package com.leysoft.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leysoft.util.SecurityUtils;

@Controller
public class BasicController {

    @GetMapping(
            value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(
            value = "/login")
    public String login(HttpSession session, @RequestParam(
            value = SecurityUtils.Name.PROTOCOL_NAME,
            required = false) String protocol,
            @RequestParam(
                    value = SecurityUtils.Name.CLIENT_ID_NAME,
                    required = false) String clientId,
            @RequestParam(
                    value = SecurityUtils.Name.REDIRECT_URI_NAME,
                    required = false) String redirectUri,
            @RequestParam(
                    value = SecurityUtils.Name.RESPONSE_TYPE_NAME,
                    required = false) String responseType,
            @RequestParam(
                    value = SecurityUtils.Name.SCOPE_NAME,
                    required = false) String scope) {
        if (SecurityUtils.isAuthenticated()) {
            return SecurityUtils.Session.redirectOperation(protocol, clientId, redirectUri,
                    responseType, scope);
        }
        SecurityUtils.Session.loginOperation(protocol, clientId, redirectUri, responseType, scope,
                session);
        return "login";
    }

    @GetMapping(
            value = "/denied")
    public String denied() {
        return "denied";
    }
}
