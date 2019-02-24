
package com.leysoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    @GetMapping(
            value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(
            value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(
            value = "/denied")
    public String denied() {
        return "denied";
    }
}
