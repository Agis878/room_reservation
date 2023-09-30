package com.example.app.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "user_login", unique = true)
    private String userLogin;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "role")
    private String role;

    @NotBlank
    private String password;
    // @EqualsAndHashcode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

}




