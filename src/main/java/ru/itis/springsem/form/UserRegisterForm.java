package ru.itis.springsem.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterForm {
    private String register_first_name;
    private String register_last_name;
    private String register_email;
    private String register_password;
}
