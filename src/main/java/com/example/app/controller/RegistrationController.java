package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.repositories.UserRepository;
import com.example.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registrationUserForm(Model model) {

        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping
    public String registrationUser(@Valid User user , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        System.out.println("3");
        user.setRole("user");
        userService.save(user);
        return "redirect:/login";
    }
}
