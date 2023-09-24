package com.example.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@Data
@ToString
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "saets_qty")
    private int seatsQty;
    @Column(name = "available_status")
    private String availableStatus;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

}
