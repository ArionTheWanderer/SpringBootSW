package ru.itis.springsem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {
    public static Long ITEMS_ID = 0L;

    Long id;
    Product product;
    Integer quantity;
    Integer total;
    Size size;
}
