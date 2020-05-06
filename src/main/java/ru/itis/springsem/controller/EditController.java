package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.services.UserService;

@Controller
public class EditController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("user/{id}/edit")
    public ModelAndView getEditPage(@PathVariable long id) {
        UserDto user = userService.find(id);
        return new ModelAndView("edit", "user", user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/update-user")
    public String updateUser(@ModelAttribute("user") UserDto userDto) {
        userService.update(userDto);
        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("user/{id}/delete")
    public String deleteUser(@PathVariable long id) {
        UserDto userDto = userService.find(id);
        userService.delete(userDto);
        return "redirect:/users";
    }
}
