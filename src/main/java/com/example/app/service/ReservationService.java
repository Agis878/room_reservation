package com.example.app.service;

import com.example.app.model.Reservation;
import com.example.app.repositories.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Scheduled(cron = "0 0 * * * *") // Aktualizacja co godzinę
    public void updateReservationStatus() {
        List<Reservation> reservations = reservationRepository.findAll();
        LocalDate currentDate = LocalDate.now();

        for (Reservation reservation : reservations) {
            if (LocalDate.parse(reservation.getReservationEndDate()).isBefore(currentDate)) {
                reservation.setReservationStatus("Zakończona");
            } else {
                reservation.setReservationStatus("Aktywna");
            }
        }
        reservationRepository.saveAll(reservations);
    }
}
