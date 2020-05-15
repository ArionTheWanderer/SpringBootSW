package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springsem.model.Order;
import ru.itis.springsem.repositories.OrderRepository;

import java.util.Optional;

@Controller
public class OrderDetailsController {

    @Autowired
    private OrderRepository orderRepository;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/order-details/{id}")
    public String getOrderDetailsPage(@PathVariable long id, ModelMap modelMap) {
        Optional<Order> orderCandidate = orderRepository.findById(id);
        orderCandidate.ifPresent(order -> modelMap.addAttribute("order", order));
        return "order-details";
    }
}
