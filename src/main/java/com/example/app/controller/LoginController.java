package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.repositories.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserRepository userService;

    public LoginController(UserRepository userService) {
        this.userService = userService;
    }

        @GetMapping
    public String showLoginForm() {
        return "login-form"; // Zwraca widok formularza logowania
    }
//    @GetMapping
//    public String showLoginForm(Model model, @RequestParam Long id) {
//        model.addAttribute("user", userService.findById(id));
//        return "login-form"; // Zwraca widok formularza logowania
//    }


//    @PostMapping
//    public String login(Model model, HttpSession session, @Param("userId") Long id) {
//        Optional<User> userToLogin = userService.findById(id);
//        return "login-form";
//    }


    @PostMapping
    public String login(Model model, @Param("userLogin") String userLogin, @Param("password") String password, HttpSession session) {

        Optional<User> userToLogin = userService.findUserByLoginAndPassword(userLogin, password);

        if (userToLogin.isPresent()) {
            User user = userToLogin.get();
            String userRole = user.getRole();

            session.setAttribute("loggedUser", user);

            if ("admin".equals(userRole)) {
                return "redirect:/admin";
            } else if ("user".equals(userRole)) {
                System.out.println("widok usera");
                return "redirect:/user";
            }
        }

        model.addAttribute("error", "Błędne dane logowania");
                return "login-form";
    }
}

