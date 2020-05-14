package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.model.Cart;
import ru.itis.springsem.security.details.UserDetailsImpl;

import static ru.itis.springsem.dto.UserDto.from;

@Controller
public class ProfileController {
    @Autowired
    Cart cart;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String getProfilePage(ModelMap model, Authentication authentication) {
        /*if (authentication == null) {
            return "redirect:/error";
        }*/
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto userFromServer = from(details.getUser());
        model.addAttribute("cart", cart);
        model.addAttribute("userFromServer", userFromServer);
        return "my-account";
    }
}
