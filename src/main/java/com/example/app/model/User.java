package com.example.app.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(nullable = false)
    private Boolean active = false;


    // @EqualsAndHashcode.Exclude

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

}




