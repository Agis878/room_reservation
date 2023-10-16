package com.example.app.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user.
     */
    @NotBlank
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /**
     * The password of the user.
     * Must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, and one digit.
     */
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, and one digit.")
    @Column(nullable = false)
    private String password;

    /**
     * The first name of the user.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The role of the user.
     */
    @Column(name = "role", nullable = false)
    private String role;

    /**
     * The activation status of the user.
     */
    @Column(nullable = false)
    private Boolean active = false;

    /**
     * The list of reservations associated with the user.
     */
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;
}




