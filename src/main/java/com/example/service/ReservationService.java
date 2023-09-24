package com.example.service;

import com.example.model.Reservation;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ReservationService {

    public List<Reservation> findAll();
    public void addReservation();
    public void updateReservation(Long id);
    public void deleteReservation(Long id);
}
