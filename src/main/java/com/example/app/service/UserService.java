package com.example.app.service;

import com.example.app.model.User;
import com.example.app.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface UserService {


    List<User>findAll();
    Optional<User> findById(Long id) ;

    void updateUser(User user) ;

    void deleteUser(User user);

    Optional<User> findByLoginAndPassword(String login, String password)     ;

    void saveUser(User user) ;

}
