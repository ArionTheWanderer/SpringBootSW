package ru.itis.springsem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/checkout")
    public String getCheckoutPage() {
        return "checkout";
    }
}