package ru.itis.springsem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    /*HashMap<Long, ProductWithSize> productsWihSize;

    HashMap<Long, Integer> prices;

    HashMap<Long, Integer> quantities;

    HashMap<Long, Integer> totals;

    HashMap<Long, ArrayList<Size>> sizes;*/

    private List<CartItemQuan> itemsQuan;

    private Integer totals;

    public void clear() {
        itemsQuan.clear();
        totals = 0;
    }
}
