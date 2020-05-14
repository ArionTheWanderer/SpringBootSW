package ru.itis.springsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springsem.model.Order;
import ru.itis.springsem.model.Product;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
