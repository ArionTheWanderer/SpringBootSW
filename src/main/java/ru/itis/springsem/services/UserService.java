package ru.itis.springsem.services;

import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.model.User;

import java.util.List;

public interface UserService {

    boolean signUp(UserDto userDto);

    User add(UserDto user);
    UserDto find(long id);
    User update(UserDto user);
    /*void delete(UserDto user);*/

    boolean loginHasCorrectLength(UserDto userDto);

    List<UserDto> getAll();

    UserDto get(String login, String password);

    boolean validate(UserDto user);

    void filterByNickName(List<UserDto> userDtoList, String name);
}
