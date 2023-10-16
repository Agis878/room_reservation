package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table(name = "rooms")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    /**
     * The unique identifier for the room.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the room.
     */
    private String name;

    /**
     * The quantity of seats in the room.
     */
    @Column(name = "saets_qty")
    private int seatsQty;

    /**
     * The availability status of the room.
     */
    @Column(name = "available_status")
    private String availableStatus;

    /**
     * The list of reservations associated with the room.
     */
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    /**
     * Returns a string representation of the room.
     */
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
