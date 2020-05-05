package ru.itis.springsem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.springsem.form.UserLoginForm;
import ru.itis.springsem.form.UserProfileForm;
import ru.itis.springsem.form.UserRegisterForm;
import ru.itis.springsem.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String login;
    private String email;
    private String password;
    private String role;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().name())
                .build();
    }

    public static UserDto fromRegisterForm(UserRegisterForm userRegisterForm) {
        return UserDto.builder()
                .login(userRegisterForm.getRegister_login())
                .email(userRegisterForm.getRegister_email())
                .password(userRegisterForm.getRegister_password())
                .build();
    }

    public static UserDto fromLoginForm(UserLoginForm userLoginForm) {
        return UserDto.builder()
                .email(userLoginForm.getEmail())
                .password(userLoginForm.getPassword())
                .build();
    }

    public static UserDto fromProfileForm(UserProfileForm userProfileForm) {
        return UserDto.builder()
                .login(userProfileForm.getProfile_login())
                .email(userProfileForm.getProfile_email())
                .password(userProfileForm.getNew_pass())
                .build();
    }
}
