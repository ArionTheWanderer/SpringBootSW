package ru.itis.springsem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springsem.model.Size;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Long> {
    List<Size> findAll();
}
