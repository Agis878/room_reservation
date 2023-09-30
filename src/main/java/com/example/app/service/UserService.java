package com.example.app.service;

import com.example.app.model.User;


import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    void updateUser(User user);

    void deleteUser(User user);

    Optional<User> findByLoginAndPassword(String login, String password);

    User save(User user);

}
