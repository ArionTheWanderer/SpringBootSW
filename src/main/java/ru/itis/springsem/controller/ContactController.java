package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springsem.model.Cart;

@Controller
public class ContactController {
    @Autowired
    Cart cart;

    @PreAuthorize("permitAll()")
    @GetMapping("/contact-us")
    public String getContactPage(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        modelMap.addAttribute("authentication", authentication);
        modelMap.addAttribute("cart", cart);
        return "contact-us";
    }
}
