package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springsem.dto.ProductDto;
import ru.itis.springsem.form.ProductForm;
import ru.itis.springsem.model.Cart;
import ru.itis.springsem.model.Product;
import ru.itis.springsem.model.ProductWithSize;
import ru.itis.springsem.model.Size;
import ru.itis.springsem.services.CartService;
import ru.itis.springsem.services.ProductService;
import ru.itis.springsem.services.SizeService;

import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    Cart cart;

    @Autowired
    CartService cartService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart")
    public String getCartPage(ModelMap modelMap) {
        modelMap.addAttribute("cart", cart);
        return "cart";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-to-cart")
    public String addToCart(ProductForm productForm, ModelMap modelMap) {
        ProductDto productDto = ProductDto.fromProductForm(productForm);
        cartService.addItem(productDto);
        modelMap.addAttribute("cart", cart);
        return "redirect:/cart";
    }
}
