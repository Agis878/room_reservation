package com.example.app.repositories;

import com.example.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long>{


   @Query("SELECT u FROM User u WHERE u.userLogin = :login AND u.password = :password")
    Optional<User> findUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
