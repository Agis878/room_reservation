package com.example.service;

import com.example.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface UserService {
    public List<User>findAll();
    public Optional<User> findById(Long id);
    public void updateUser(Long id);
    public void deleteUser(Long id);
    public Optional<User> findByLoginAndPassword(String login, String password);

    public void saveUser();

}
