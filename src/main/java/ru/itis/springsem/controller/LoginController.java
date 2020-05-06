package ru.itis.springsem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.RequestScope;

@Controller
public class LoginController {

    @RequestScope
    @GetMapping("/login")
    public String getLoginPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "login-register";
    }
}
