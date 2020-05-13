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
    Product product;
    Size size;


    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product.getName() + product.getCost() + " " + product.getDimensions()
                + " " + product.getFullDescription() + " " + product.getShortDescription() + product.getId().toString() +
                ", size=" + size.getSize().name() +
                '}';
    }
}
