package ru.itis.springsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springsem.model.Category;
import ru.itis.springsem.model.Color;
import ru.itis.springsem.model.Product;
import ru.itis.springsem.repositories.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getByColor(Color color) {
        return productRepository.findAllByColors(color);
    }

    @Override
    public List<Product> getByCategory(Category category) {
        return productRepository.findAllByCategories(category);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
