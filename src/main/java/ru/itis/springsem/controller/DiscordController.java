package ru.itis.springsem.controller;

import bell.oauth.discord.main.OAuthBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.springsem.model.Role;
import ru.itis.springsem.model.User;
import ru.itis.springsem.security.details.UserDetailsImpl;
import ru.itis.springsem.services.UserService;

@Controller
public class DiscordController {

    @Autowired
    OAuthBuilder builder;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/discord/auth")
    public String getDiscordAuthPage(@RequestParam("code") String code)  {
        builder.exchange(code);
        User user = userService.getUserByEmail(builder.getUser().getEmail());
        if (user != null) {
            UserDetailsImpl userDetails = new UserDetailsImpl(user);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, user.getEmail(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            return "redirect:/";
        } else {
            User newUser = User.builder()
                    .email(builder.getUser().getEmail())
                    .password(passwordEncoder.encode(builder.getUser().getEmail()))
                    .firstName(builder.getUser().getUsername())
                    .role(Role.USER)
                    .build();
            userService.save(newUser);
            UserDetailsImpl newUserDetails = new UserDetailsImpl(newUser);
            UsernamePasswordAuthenticationToken newToken = new UsernamePasswordAuthenticationToken(newUserDetails, newUser.getPassword(), newUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newToken);
        }
        return "redirect:/";
    }
}
