package com.example.app.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reservation_start")
    private LocalDate reservationStartDate;
//    private String reservationStartDate;


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

    @PrePersist
    public void prePersist() {
        reservationDate = LocalDate.from(LocalDateTime.now());
        if (reservationEndDate.isAfter(LocalDate.now())) {
            reservationStatus = "Aktywny";
        } else {
            reservationStatus = "Zakończony";
        }
    }

//    @Override
//    public String toString() {
//        return "Reservation{" +
//                "id=" + id +
//                ", reservationStartDate=" + reservationStartDate +
//                ", reservationEndDate=" + reservationEndDate +
//                ", reservationStatus='" + reservationStatus + '\'' +
//                '}';
//    }

}
