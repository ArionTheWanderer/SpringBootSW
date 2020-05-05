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
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole().name())
                .build();
    }

    public static UserDto fromRegisterForm(UserRegisterForm userRegisterForm) {
        return UserDto.builder()
                .firstName(userRegisterForm.getRegister_first_name())
                .lastName(userRegisterForm.getRegister_last_name())
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
                .firstName(userProfileForm.getProfile_first_name())
                .lastName(userProfileForm.getProfile_last_name())
                .email(userProfileForm.getProfile_email())
                .password(userProfileForm.getNew_pass())
                .build();
    }
}
