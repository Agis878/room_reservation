package com.example.app.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@Data
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

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seatsQty=" + seatsQty +
                ", availableStatus='" + availableStatus + '\'' +
                ", reservations=" + reservations +
                '}';
    }
}
