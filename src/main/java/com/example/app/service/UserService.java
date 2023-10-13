package com.example.app.service;

import com.example.app.model.User;


import java.util.List;
import java.util.Optional;


public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    void updateUser(User user);

    void deleteUser(User user);

    User save(User user);

    User getByUsername(String name);

    User getUserWithReservationsByUserName(String name);

    boolean isUsernameUnique(String username);

}
