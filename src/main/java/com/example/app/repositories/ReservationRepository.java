package com.example.app.repositories;

import com.example.app.model.Reservation;
import com.example.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository <Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.user = :user")
    List<Reservation> findAllByUser(@Param("user") User user);


//    List<Reservation> findAllByUser(User user);
}
