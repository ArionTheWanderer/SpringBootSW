package ru.itis.springsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springsem.model.Category;
import ru.itis.springsem.model.Color;
import ru.itis.springsem.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    List<Product> findAllByCategories(Category category);
    List<Product> findAllByColors(Color color);
}
