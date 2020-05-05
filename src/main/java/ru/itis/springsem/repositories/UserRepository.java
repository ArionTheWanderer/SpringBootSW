package ru.itis.springsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springsem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserById(Long id);
    List<User> findAll();
    Optional<User> findUserByLogin(String login);
}

