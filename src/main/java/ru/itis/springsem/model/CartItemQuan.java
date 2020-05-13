package ru.itis.springsem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemQuan {
    public static Long ITEMS_ID = 0L;

    Long id;

    CartItem cartItem;
    Integer quantity;
    Integer total;
}
