package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.repositories.UserRepository;
import com.example.app.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login-form"; // Zwraca widok formularza logowania
    }


    @PostMapping("/login")
    public String login(Model model, @Param("userLogin") String userLogin, @Param("password") String password, HttpSession session) {

        Optional<User> userToLogin = userService.findByLoginAndPassword(userLogin, password);

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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Usuń sesję
        }
        return "redirect:/login";
    }
}

