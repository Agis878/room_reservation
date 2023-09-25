package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.repositories.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showLoginForm() {
        return "login-form"; // Zwraca widok formularza logowania
    }

    @PostMapping
    public String login(Model model, @Param("userLogin") String userLogin, @Param("password") String password) {

        Optional<User> userToLogin = userRepository.findUserByLoginAndPassword(userLogin, password);

//        model.addAttribute("user", new User());
        if (userToLogin.isPresent()) {
            User user = userToLogin.get();
            String userRole = user.getRole();
            if ("admin".equals(userRole)) {

                return "redirect:/login/admin";
            } else if ("user".equals(userRole)) {

                System.out.println("widok usera");
                return "redirect:/login/user";
            }
        }

        model.addAttribute("error", "Błędne dane logowania");
                return "login-form";
    }
}

