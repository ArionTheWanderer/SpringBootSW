package ru.itis.springsem.services;

import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean signUp(UserDto userDto);

    UserDto add(UserDto user);
    UserDto find(long id);
    UserDto update(UserDto user);
    void delete(UserDto user);

    List<UserDto> getAll();

    void save(User user);

    User getUserByEmail(String email);

    UserDto get(String login, String password);

    boolean validate(UserDto user);

}
