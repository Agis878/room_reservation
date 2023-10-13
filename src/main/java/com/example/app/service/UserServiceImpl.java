package com.example.app.service;

import com.example.app.model.User;
import com.example.app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    public void updateUser(User user) {
        userRepository.save(user);
    }
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    public User getUserWithReservationsByUserName(String name) {
        return userRepository.getWithReservationsByUsername(name);
    }

    @Override
    public boolean isUsernameUnique(String username) {
        // Check if the username is unique by querying the database
        User existingUser = userRepository.getByUsername(username);
        return existingUser == null;
    }

}
