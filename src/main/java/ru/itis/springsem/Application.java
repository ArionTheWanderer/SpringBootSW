package ru.itis.springsem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;
import ru.itis.springsem.model.Cart;
import ru.itis.springsem.model.CartItem;
import ru.itis.springsem.model.ProductWithSize;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {
    @Bean
    @ApplicationScope
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ApplicationScope
    public Cart cart() {
        List<CartItem> items = new ArrayList<>();
        return new Cart(items, 0);
    }

    /*@Bean
    @Scope("prototype")
    public ProductWithSize productWithSize() {
        ProductWithSize productWithSize = new ProductWithSize();
        ProductWithSize.id++;
        System.out.println("ProductWithSizeId is: " + ProductWithSize.id);
        return productWithSize;
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
