package ru.itis.springsem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.springsem.dto.UserDto;
import ru.itis.springsem.services.UserService;

import java.util.List;

@Controller
public class ShowListController {

    @Autowired UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public ModelAndView getPage(@RequestParam(value = "filter", required = false) String subString) {
        List<UserDto> userDtoList = userService.getAll();
        /*if (subString != null) {
            userService.filterByNickName(userDtoList, subString);
        }*/
        ModelAndView mav = new ModelAndView("list");
        mav.addObject("users", userDtoList);
        return mav;
    }

}
