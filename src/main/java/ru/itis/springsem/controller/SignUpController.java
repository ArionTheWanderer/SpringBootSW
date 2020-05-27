package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.form.UserRegisterForm;
import ru.itis.springsem.services.ValidatorService;
import ru.itis.springsem.services.UserService;

@Controller
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidatorService validatorService;

    @PreAuthorize("permitAll()")
    @PostMapping("/signUp")
    public String signIn(UserRegisterForm userRegisterForm, ModelMap model) {
        UserDto userDto = UserDto.fromRegisterForm(userRegisterForm);
        if(validatorService.isEmailValid(userRegisterForm.getRegister_email().toCharArray()) &&
                userService.signUp(userDto)) {
            System.out.println("ИМЭЙЛ ПРОШЕЛ");
            return "redirect:/";
        } else return "redirect:/";
    }
}
