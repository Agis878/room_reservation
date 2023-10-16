package com.example.app.service;

import com.example.app.model.Reservation;
import com.example.app.model.User;
import com.example.app.repositories.ReservationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    // Retrieve reservations based on status (active, finished, or all)
    public List<Reservation> findAllByReservationStatus(String reservationStatus) {

        if (reservationStatus.equals("active")) {
            return reservationRepository.findAllByReservationStatus(reservationStatus);

        } else if (reservationStatus.equals("finished")) {
            return reservationRepository.findAllByReservationStatus(reservationStatus);

        } else {
            return reservationRepository.findAll();
        }
    }

    // Add or update a reservation
    public boolean addReservation(Reservation reservation) {
        if (isValidReservation(reservation)) {
            reservation.prePersist();

            if (reservation.getId() != null) {
                // If reservation has an ID, it means it already exists, so update it
                Optional<Reservation> existingReservation = reservationRepository.findById(reservation.getId());
                if (existingReservation.isPresent()) {
                    Reservation oldReservation = existingReservation.get();
                    oldReservation.setReservationStartDate(reservation.getReservationStartDate());
                    oldReservation.setReservationEndDate(reservation.getReservationEndDate());
                    oldReservation.setReservationDate(reservation.getReservationDate());
                    oldReservation.setRoom(reservation.getRoom());

                    reservationRepository.save(oldReservation);
                    return true;
                }
            }

            // If the reservation does not have an ID or does not exist, save it as a new reservation
            reservationRepository.save(reservation);
            return true;
        } else {
            return false;
        }
    }

    // Check if a reservation is valid
    boolean isValidReservation(Reservation reservation) {
        LocalDate currentDate = LocalDate.now();
        return reservation.getReservationStartDate().isBefore(reservation.getReservationEndDate()) && !reservation.getReservationStartDate().isBefore(currentDate) && isRoomAvailable(reservation);
    }

    // Check if a room is available for a given reservation
    boolean isRoomAvailable(Reservation reservation) {
        List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(reservation.getRoom(), reservation.getReservationStartDate(), reservation.getReservationEndDate(), reservation.getId());
        return overlappingReservations.isEmpty();
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> findAllByUser(User user) {
        return reservationRepository.findAllByUser(user);
    }

    // Scheduled task to update reservation status every hour
    @Scheduled(cron = "0 0 * * * *")
    public void updateReservationStatus() {
        List<Reservation> reservations = reservationRepository.findAll();
        LocalDate currentDate = LocalDate.now();

        for (Reservation reservation : reservations) {
            if (reservation.getReservationEndDate().isBefore(currentDate)) {
                reservation.setReservationStatus("finished");
            } else {
                reservation.setReservationStatus("active");
            }
        }
        reservationRepository.saveAll(reservations);
    }
}
