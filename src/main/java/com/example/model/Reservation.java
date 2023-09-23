package com.example.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
    @Table(name = "reservations")
    public class Reservation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private LocalDate reservationStartDate;
        private LocalDate reservationEndDate;
        private String reservationStatus;
        private LocalDate reservationDate;

        @ManyToOne
        @JoinColumn(name = "room_id")
        private Room room;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

}
