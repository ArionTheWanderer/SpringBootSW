package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springsem.model.Cart;
import ru.itis.springsem.model.Product;
import ru.itis.springsem.model.Size;
import ru.itis.springsem.services.ProductService;
import ru.itis.springsem.services.SizeService;


import java.util.List;
import java.util.Optional;

@Scope("customScope")
@Controller
public class ProductController {
    @Autowired
    Cart cart;

    @Autowired
    ProductService productService;

    @Autowired
    SizeService sizeService;

    @PreAuthorize("permitAll()")
    @GetMapping("/product/{id}")
    public String getProductPage(@PathVariable long id, ModelMap modelMap) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            List<Size> sizes = sizeService.getAllSizes();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            modelMap.addAttribute("authentication", authentication);
            modelMap.addAttribute("cart", cart);
            modelMap.addAttribute("product", product.get());
            modelMap.addAttribute("sizes", sizes);
            return "product-details";
        }
        return "redirect:/404";
    }
}
