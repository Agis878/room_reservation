package com.example.app.repositories;

import com.example.app.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * Retrieves a user by their username.
     */
    User getByUsername(String username);

    /**
     * Retrieves a user by their username along with associated reservations using EntityGraph.
     */
    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"reservations"})
    User getWithReservationsByUsername(String username);
}
