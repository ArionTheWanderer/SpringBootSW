package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.services.UserService;

@Controller
public class AddController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add")
    public String showAddPage() {
        return "add";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public String addUser(@ModelAttribute("userDto") UserDto userDto) {
        try {
            userService.validate(userDto);
            String password = userDto.getPassword();
            String hashPassword = passwordEncoder.encode(password);
            userDto.setPassword(hashPassword);
            userService.add(userDto);
            return "redirect:/users";
        } catch (IllegalStateException e) {
            return "redirect:/add?error";
        }

    }
}
