package com.example.app.service;

import com.example.app.model.Reservation;
import com.example.app.model.User;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Reservation> findAll();

    void addReservation(Reservation reservation);

    Optional<Reservation> findById(Long id);

    void updateReservation(Reservation reservation);

    void deleteReservation(Long id);


    void updateReservationStatus();
    List<Reservation> findAllByUser(User user);
}
