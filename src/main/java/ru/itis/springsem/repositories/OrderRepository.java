package ru.itis.springsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springsem.model.Order;
import ru.itis.springsem.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOwner(User user);
    Optional<Order> findById(Long id);
}
