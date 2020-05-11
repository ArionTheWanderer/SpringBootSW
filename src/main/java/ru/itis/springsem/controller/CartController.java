package ru.itis.springsem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart")
    public String getCartPage() {
        return "cart";
    }
}
