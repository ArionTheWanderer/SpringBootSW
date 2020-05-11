package ru.itis.springsem.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@SessionScope
@Component
public class Cart {
    List<Product> productList;

    List<Double> prices;

    List<Integer> quantities;

    List<Double> totals;
}
