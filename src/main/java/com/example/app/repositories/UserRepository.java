package com.example.app.repositories;

import com.example.app.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long>{


    User getByUsername(String username);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD, attributePaths = {"reservations"})
    User getWithReservationsByUsername(String username);

}
