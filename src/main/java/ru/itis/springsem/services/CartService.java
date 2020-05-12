package ru.itis.springsem.services;

import ru.itis.springsem.dto.ProductDto;

public interface CartService {
    void addItem(ProductDto productDto);
}
