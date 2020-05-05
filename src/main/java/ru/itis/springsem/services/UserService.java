package ru.itis.springsem.services;

import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.model.User;

import java.util.List;

public interface UserService {

    boolean signUp(UserDto userDto);

    UserDto add(UserDto user);
    UserDto find(long id);
    UserDto update(UserDto user);
    void delete(UserDto user);

    List<UserDto> getAll();

    UserDto get(String login, String password);

    boolean validate(UserDto user);

}
