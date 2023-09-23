package com.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int seatsQty;
    private String availableStatus;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

}
