package com.example.app.service;

import com.example.app.model.User;
import com.example.app.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setActive(true);
        return userRepository.save(user);
    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    /**
     * Retrieves a user by their username along with associated reservations.
     */
    public User getUserWithReservationsByUserName(String name) {
        return userRepository.getWithReservationsByUsername(name);
    }

    /**
     * Checks if a username is unique.
     *
     * @param username The username to be checked.
     * @return True if the username is unique; otherwise, false.
     */
    public boolean isUsernameUnique(String username) {
        User existingUser = userRepository.getByUsername(username);
        return existingUser == null;
    }

}
