package com.example.app.controller;

import com.example.app.model.User;
import com.example.app.repositories.UserRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;

import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String loginForm(Model model, @Param("userLogin") String login, @Param("password") String password) {

        Optional<User> userToLog = userRepository.findUserByLoginAndPassword(login, password);

        model.addAttribute("user", new User());
        if (userToLog.isPresent()) {
            User user = userToLog.get();
            String userRole = user.getRole();
            if (userRole.equals("admin")) {
                model.addAttribute("userAdmin", user);
                return "/admin-view";
            } else {
                return "/user-view";
            }
        } else {
            throw new EntityNotFoundException();
        }
    }


}
