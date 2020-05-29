package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.RequestScope;
import ru.itis.springsem.model.Cart;

@Scope("customScope")
@Controller
public class LoginController {

    @Autowired
    Cart cart;

    @RequestScope
    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, ModelMap modelMap) {
        modelMap.addAttribute("cart", cart);
        if (authentication != null) {
            return "redirect:/";
        }
        return "login-register";
    }
}
