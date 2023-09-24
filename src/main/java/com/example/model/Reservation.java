package com.example.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
    @Table(name = "reservations")
    public class Reservation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "reservation_start")
        private LocalDate reservationStartDate;
    @Column(name = "reservation_end")
        private LocalDate reservationEndDate;
    @Column(name = "reservation_status")
        private String reservationStatus;
    @Column(name = "reservation_date")
        private LocalDate reservationDate;

        @ManyToOne
        @JoinColumn(name = "room_id")
        private Room room;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

}
