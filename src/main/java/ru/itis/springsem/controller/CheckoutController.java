package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springsem.model.Cart;

@Controller
public class CheckoutController {
    @Autowired
    Cart cart;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/checkout")
    public String getCheckoutPage(ModelMap modelMap) {
        modelMap.addAttribute("cart", cart);
        return "checkout";
    }
}
