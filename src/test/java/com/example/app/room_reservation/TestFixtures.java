package com.example.app.room_reservation;


import com.example.app.model.Reservation;
import com.example.app.model.Room;
import com.example.app.model.User;

import java.time.LocalDate;

public class TestFixtures {


    public static Room room() {
        return Room.builder()
                .name("Room1")
                .seatsQty(43)
                .build();
    }


    public static User user() {
        return User.builder()
                .active(true)
                .role("ROLE_USER")
                .username("Ala")
                .firstName("Alicja")
                .lastName("Kowalska")
                .password("{noop}ala")
                .build();
    }

    public static Reservation reservation(Room room, User user) {
        Reservation reservation = Reservation.builder()

                .room(room)
                .reservationStartDate(LocalDate.now().plusDays(7))
                .reservationEndDate(LocalDate.now().plusDays(9))
                .user(user).build();

        reservation.prePersist();
        return reservation;

    }


}
