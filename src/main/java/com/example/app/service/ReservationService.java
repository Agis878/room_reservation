package com.example.app.service;

import com.example.app.model.Reservation;
import com.example.app.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
    public void addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }
    public void findById(Long id) {
        reservationRepository.findById(id);
    }
    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
