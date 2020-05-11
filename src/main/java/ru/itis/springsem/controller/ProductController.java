package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springsem.model.Product;
import ru.itis.springsem.services.ProductService;

import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize("permitAll()")
    @GetMapping("/product/{id}")
    public String getProductPage(@PathVariable long id, ModelMap modelMap) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            modelMap.addAttribute("product", product.get());
            return "product-details";
        }
        return "redirect:/404";
    }
}
