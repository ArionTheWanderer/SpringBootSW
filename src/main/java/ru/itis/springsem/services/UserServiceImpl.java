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

    private static final int FIRST_NAME_MIN_LENGTH = 5;
    private static final int LAST_NAME_MIN_LENGTH = 5;
    private static final int PASSWORD_MIN_LENGTH = 5;

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
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
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
    public UserDto add(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(Role.valueOf(userDto.getRole()))
                .build();
        userRepository.save(user);
        return UserDto.from(user);
    }

    @Override
    @LoggableServiceMethod
    public UserDto find(long id) {
        Optional<User> user = userRepository.findUserById(id);
        if (user.isPresent()) {
            return UserDto.from(user.get());
        } else throw new IllegalArgumentException("User not found");
    }

    @Override
    @LoggableServiceMethod
    public UserDto update(UserDto userDto) {
        Optional<User> userCandidate = userRepository.findUserById(userDto.getId());
        if (userCandidate.isPresent()) {
                User user = userCandidate.get();
                user.setId(userDto.getId());
                user.setFirstName(userDto.getFirstName());
                user.setLastName(userDto.getLastName());
                user.setEmail(userDto.getEmail());
                user.setPassword(userDto.getPassword());
                user.setRole(Role.valueOf(userDto.getRole()));
                userRepository.save(user);
                return UserDto.from(user);
        } else throw new IllegalArgumentException("User not found");
    }

    @Override
    public void delete(UserDto userDto) {
        Optional<User> user = userRepository.findById(userDto.getId());
        user.ifPresent(value -> userRepository.delete(value));
    }

    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> userCandidate = userRepository.findUserByEmail(email);
        return userCandidate.orElse(null);
    }



    @Override
    @LoggableServiceMethod
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
        return (firstNameHasCorrectLength(userDto) && lastNameHasCorrectLength(userDto)
                && emailIsAvailable(userDto) && passwordIsValid(userDto));
    }

    private boolean firstNameHasCorrectLength(UserDto userDto) {
        return userDto.getFirstName().length() >= FIRST_NAME_MIN_LENGTH;
    }

    private boolean lastNameHasCorrectLength(UserDto userDto) {
        return userDto.getLastName().length() >= LAST_NAME_MIN_LENGTH;
    }

    private boolean emailIsAvailable(UserDto userDto) {
        return !userRepository.findUserByEmail(userDto.getEmail()).isPresent();
    }

    private boolean passwordIsValid(UserDto userDto) {
        boolean correctLength = userDto.getPassword().length() >= PASSWORD_MIN_LENGTH;
        boolean containLetter = userDto.getPassword().matches(".*\\d+.*");
        boolean containDigit = userDto.getPassword().matches(".*[a-zA-Z]+.*");
        return correctLength && containDigit && containLetter;
    }

}
