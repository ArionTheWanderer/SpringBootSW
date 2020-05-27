package ru.itis.springsem.services;

import ru.itis.springsem.model.Category;
import ru.itis.springsem.model.CategoryEnum;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category findFirstByCategory(CategoryEnum category);
}
