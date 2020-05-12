package ru.itis.springsem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductWithSize {
    public static Long id = 0L;

    Product product;

    Size size;
}
