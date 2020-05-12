package ru.itis.springsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.springsem.model.Size;
import ru.itis.springsem.repositories.SizeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepository sizeRepository;

    @Override
    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    @Override
    public Optional<Size> getSizeById(Long id) {
        return sizeRepository.findById(id);
    }
}
