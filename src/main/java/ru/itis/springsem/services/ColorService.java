package ru.itis.springsem.services;

import ru.itis.springsem.model.Color;
import ru.itis.springsem.model.ColorEnum;

import java.util.List;

public interface ColorService {
    List<Color> getAll();
    Color findFirstByColor(ColorEnum color);
}
