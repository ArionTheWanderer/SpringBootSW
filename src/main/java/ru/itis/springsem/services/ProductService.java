package ru.itis.springsem.services;

import ru.itis.springsem.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void filterByCategory();

    Optional<Product> findById(Long id);

    List<Product> getAllProducts();
}
