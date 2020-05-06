package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.form.UserProfileForm;
import ru.itis.springsem.model.User;
import ru.itis.springsem.security.details.UserDetailsImpl;
import ru.itis.springsem.services.UserService;

@Controller
public class UpdateController {

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update")
    public String updateUser(UserProfileForm userProfileForm) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        user.setFirstName(userProfileForm.getProfile_first_name());
        user.setLastName(userProfileForm.getProfile_last_name());
        user.setEmail(userProfileForm.getProfile_email());
        UserDto userDto = UserDto.from(user);
        userService.update(userDto);
        return "redirect:/";
    }
}
