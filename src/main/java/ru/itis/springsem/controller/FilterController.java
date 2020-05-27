package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springsem.model.*;
import ru.itis.springsem.services.CategoryService;
import ru.itis.springsem.services.ColorService;
import ru.itis.springsem.services.ProductService;

import java.util.List;

@Controller
public class FilterController {

    @Autowired
    Cart cart;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ColorService colorService;

    @PreAuthorize("permitAll()")
    @GetMapping("/filter/category/{name}")
    public String filterByCategory(@PathVariable String name, ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Category category = categoryService.findFirstByCategory(CategoryEnum.valueOf(name));
        List<Product> products = productService.getByCategory(category);
        List<Category> categories = categoryService.getAll();
        modelMap.addAttribute("authentication", authentication);
        modelMap.addAttribute("cart", cart);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categories);
        return "shop";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/filter/color/{name}")
    public String filterByColor(@PathVariable String name, ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Category> categories = categoryService.getAll();
        Color color = colorService.findFirstByColor(ColorEnum.valueOf(name));
        List<Product> products = productService.getByColor(color);
        modelMap.addAttribute("authentication", authentication);
        modelMap.addAttribute("cart", cart);
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categories);
        return "shop";
    }
}
