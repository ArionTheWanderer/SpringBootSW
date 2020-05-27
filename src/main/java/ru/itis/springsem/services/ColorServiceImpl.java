package ru.itis.springsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springsem.model.Color;
import ru.itis.springsem.model.ColorEnum;
import ru.itis.springsem.repositories.ColorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<Color> getAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color findFirstByColor(ColorEnum color) {
        Optional<Color> colorOptional = colorRepository.findFirstByColor(color);
        return colorOptional.orElse(null);
    }
}
