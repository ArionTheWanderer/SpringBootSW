package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springsem.model.Cart;
import ru.itis.springsem.model.Product;
import ru.itis.springsem.services.ProductService;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    Cart cart;

    @Autowired
    ProductService productService;

    @PreAuthorize("permitAll()")
    @GetMapping("/shop")
    public String getShopPage(ModelMap modelMap) {
        List<Product> products = productService.getAllProducts();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        modelMap.addAttribute("authentication", authentication);
        modelMap.addAttribute("cart", cart);
        modelMap.addAttribute("products", products);
        return "shop";
    }
}
