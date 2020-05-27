package ru.itis.springsem.services;

import ru.itis.springsem.model.Category;
import ru.itis.springsem.model.Color;
import ru.itis.springsem.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getByColor(Color color);

    List<Product> getByCategory(Category category);

    Optional<Product> findById(Long id);

    List<Product> getAllProducts();
}
