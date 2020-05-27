package ru.itis.springsem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springsem.model.Color;
import ru.itis.springsem.model.ColorEnum;

import java.util.List;
import java.util.Optional;

public interface ColorRepository extends JpaRepository<Color, Long> {
    List<Color> findAll();
    Optional<Color> findFirstByColor(ColorEnum color);
}
