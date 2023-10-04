package com.example.app.service;

import com.example.app.model.Reservation;
import com.example.app.model.User;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Reservation> findAll();

    List<Reservation>findAllByReservationStatus(String reservationStatus);

    boolean addReservation(Reservation reservation);

    Optional<Reservation> findById(Long id);

//    void updateReservation(Reservation reservation);

//    boolean updateReservation(Reservation updatedReservation);

    void deleteReservation(Long id);


    List<Reservation> findAllByUser(User user);


}
