package ru.itis.springsem.services;

import org.springframework.stereotype.Service;
import ru.itis.springsem.model.Size;

import java.util.List;

public interface SizeService {
    List<Size> getAllSizes();
}
