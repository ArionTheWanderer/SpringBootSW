package ru.itis.springsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springsem.model.Category;
import ru.itis.springsem.model.CategoryEnum;
import ru.itis.springsem.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findFirstByCategory(CategoryEnum category) {
        Optional<Category> categoryOptional = categoryRepository.findFirstByCategory(category);
        return categoryOptional.orElse(null);
    }
}
