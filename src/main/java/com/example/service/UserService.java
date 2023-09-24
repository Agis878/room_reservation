package com.example.service;

import com.example.model.User;
import com.example.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User>findAll() {
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
    public Optional<User> findByLoginAndPassword(String login, String password) {
       return userRepository.findUserByLoginAndPassword(login,password);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
