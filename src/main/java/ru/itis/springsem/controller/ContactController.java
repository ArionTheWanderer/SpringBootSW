package ru.itis.springsem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
    @PreAuthorize("permitAll()")
    @GetMapping("/contact-us")
    public String getContactPage() {
        return "contact-us";
    }
}
