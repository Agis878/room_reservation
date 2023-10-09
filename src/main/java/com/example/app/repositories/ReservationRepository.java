package com.example.app.repositories;

import com.example.app.model.Reservation;
import com.example.app.model.Room;
import com.example.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository <Reservation, Long> {

    List<Reservation> findAllByUser(User user);

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.room = :room " +
            "AND ((r.reservationStartDate <= :endDate AND r.reservationEndDate >= :startDate) " +
            "OR (r.reservationStartDate >= :startDate AND r.reservationStartDate <= :endDate)) " +
            "AND (:reservationId IS NULL OR r.id <> :reservationId)")
    List<Reservation> findOverlappingReservations(
            @Param("room") Room room,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("reservationId") Long reservationId);
    @Query("SELECT r FROM Reservation r where r.reservationStatus = :status")
    List<Reservation>findAllByReservationStatus(@Param("status") String status);


}



