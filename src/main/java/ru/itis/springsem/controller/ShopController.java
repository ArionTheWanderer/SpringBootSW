package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springsem.model.Cart;
import ru.itis.springsem.model.Category;
import ru.itis.springsem.model.Color;
import ru.itis.springsem.model.Product;
import ru.itis.springsem.repositories.ColorRepository;
import ru.itis.springsem.services.CategoryService;
import ru.itis.springsem.services.ColorService;
import ru.itis.springsem.services.ProductService;

import java.util.List;

@Scope("customScope")
@Controller
public class ShopController {

    @Autowired
    Cart cart;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ColorService colorService;

    @PreAuthorize("permitAll()")
    @GetMapping("/shop")
    public String getShopPage(ModelMap modelMap) {
        List<Product> products = productService.getAllProducts();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Category> categories = categoryService.getAll();
        modelMap.addAttribute("authentication", authentication);
        modelMap.addAttribute("cart", cart);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categories);
        return "shop";
    }
}
