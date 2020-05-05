package ru.itis.springsem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.springsem.aspect.LoggableServiceMethod;
import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.model.Role;
import ru.itis.springsem.model.User;
import ru.itis.springsem.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    public static final int LOGIN_MIN_LENGTH = 5;
    public static final int PASSWORD_MIN_LENGTH = 5;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @LoggableServiceMethod
    public boolean signUp(UserDto userDto) {
        if (validate(userDto)) {
            String hashPassword = passwordEncoder.encode(userDto.getPassword());

            User user = User.builder()
                    .login(userDto.getLogin())
                    .email(userDto.getEmail())
                    .password(hashPassword)
                    .role(Role.USER)
                    .build();

            userRepository.save(user);
            return true;
        } else return false;
    }

    @Override
    @LoggableServiceMethod
    public User add(UserDto userDto) {
        User user = User.builder()
                .login(userDto.getLogin())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(Role.valueOf(userDto.getRole()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public UserDto find(long id) {
        Optional<User> user = userRepository.findUserById(id);
        if (user.isPresent()) {
            return UserDto.from(user.get());
        } else throw new IllegalArgumentException("User not found");
    }

    @Override
    @LoggableServiceMethod
    public User update(UserDto userDto) {
        Optional<User> userCandidate = userRepository.findUserById(userDto.getId());
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            user.setId(userDto.getId());
            user.setLogin(userDto.getLogin());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setRole(Role.valueOf(userDto.getRole()));
            return userRepository.save(user);
        } else return new User();
    }

    /*@Override
    public void delete(UserDto userDto) {
        User user = userRepository.find(userDto.getId());
        userRepository.delete(user);
    }*/

    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto get(String email, String password) {
        Optional<User> userCandidate = userRepository.findUserByEmail(email);
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (user.getPassword().equals(password))
                return UserDto.from(user);
        }
        throw new IllegalArgumentException("User not found");
    }

    @Override
    @LoggableServiceMethod
    public boolean validate(UserDto userDto) {
        return (loginIsAvailable(userDto) && loginHasCorrectLength(userDto)
                && emailIsAvailable(userDto) && passwordIsValid(userDto));
    }

    @Override
    public void filterByNickName(List<UserDto> userDtoList, String name) {
        userDtoList.removeIf(userDto -> !userDto.getLogin().contains(name));
    }

    @Override
    public boolean loginHasCorrectLength(UserDto userDto) {
        return userDto.getLogin().length() >= LOGIN_MIN_LENGTH;
    }

    private boolean emailIsAvailable(UserDto userDto) {
        return !userRepository.findUserByEmail(userDto.getEmail()).isPresent();
    }

    private boolean loginIsAvailable(UserDto userDto) {
        return !userRepository.findUserByLogin(userDto.getLogin()).isPresent();
    }

    private boolean passwordIsValid(UserDto userDto) {
        boolean correctLength = userDto.getPassword().length() >= PASSWORD_MIN_LENGTH;
        boolean containLetter = userDto.getPassword().matches(".*\\d+.*");
        boolean containDigit = userDto.getPassword().matches(".*[a-zA-Z]+.*");
        return correctLength && containDigit && containLetter;
    }

}
