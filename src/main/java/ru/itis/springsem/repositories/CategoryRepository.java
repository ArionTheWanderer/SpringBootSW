package ru.itis.springsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springsem.model.Category;
import ru.itis.springsem.model.CategoryEnum;
import ru.itis.springsem.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();
    Optional<Category> findFirstByCategory(CategoryEnum category);
}
