package com.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userLogin;
    private String firstName;
    private String lastName;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

    }




