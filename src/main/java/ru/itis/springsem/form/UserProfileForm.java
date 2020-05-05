package ru.itis.springsem.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileForm {
    private String profile_login;
    private String profile_email;
    private String cur_pass;
    private String new_pass;
    private String conf_new_pass;
}
