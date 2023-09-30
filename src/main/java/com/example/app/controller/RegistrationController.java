package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserRepository userService;
    public RegistrationController(UserRepository userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registrationUserForm(Model model) {

        model.addAttribute("user", new User());
        System.out.println("1");
        return "registration";
    }

    @PostMapping
    public String registrationUser(@Valid User user , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("2");
            return "registration";
        }
        System.out.println("3");
        user.setRole("user");
        userService.save(user);
        return "redirect:/login";
    }
}
