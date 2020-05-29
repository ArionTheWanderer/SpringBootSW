package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.model.Cart;
import ru.itis.springsem.model.Order;
import ru.itis.springsem.repositories.OrderRepository;
import ru.itis.springsem.security.details.UserDetailsImpl;

import java.util.List;

import static ru.itis.springsem.dto.UserDto.from;

@Scope("customScope")
@Controller
public class ProfileController {
    @Autowired
    Cart cart;

    @Autowired
    private OrderRepository orderRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String getProfilePage(ModelMap model, Authentication authentication) {
        UserDetailsImpl details = (UserDetailsImpl)authentication.getPrincipal();
        UserDto userFromServer = from(details.getUser());
        List<Order> orders = orderRepository.findAllByOwner(details.getUser());
        model.addAttribute("orders", orders);
        model.addAttribute("cart", cart);
        model.addAttribute("userFromServer", userFromServer);
        return "my-account";
    }
}
