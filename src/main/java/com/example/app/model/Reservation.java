package com.example.app.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    /**
     * The unique identifier for the reservation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The start date of the reservation.
     */
    @NotNull(message = "Start date cannot be blank")
    @FutureOrPresent(message = "Start date must be in the present or future")
    @Column(name = "reservation_start")
    private LocalDate reservationStartDate;

    /**
     * The end date of the reservation.
     */
    @NotNull(message = "End date cannot be blank")
    @FutureOrPresent(message = "End date must be in the present or future")
    @Column(name = "reservation_end")
    private LocalDate reservationEndDate;

    /**
     * The status of the reservation (active or finished).
     */
    @Column(name = "reservation_status")
    private String reservationStatus;

    /**
     * The date when the reservation was made.
     */
    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    /**
     * The room associated with the reservation.
     */
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    /**
     * The user who made the reservation.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Sets default values before persisting the reservation.
     */
    @PrePersist
    public void prePersist() {
        reservationDate = LocalDate.now();
        if (reservationEndDate.isAfter(LocalDate.now())) {
            reservationStatus = "active";
        } else {
            reservationStatus = "finished";
        }
    }

    /**
     * Returns a string representation of the reservation.
     */
    @Override
    public String toString() {

        String roomName = Optional.ofNullable(room).map(Room::getName).orElse("null");
        return "Reservation{" + "id=" + id + ", reservationStartDate=" + reservationStartDate + ", reservationEndDate=" + reservationEndDate + ", reservationStatus='" + reservationStatus + '\'' + ", room=" + roomName + '}';
    }
}
