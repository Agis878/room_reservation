package com.example.app.room_reservation.repository;

import com.example.app.model.Reservation;
import com.example.app.model.Room;
import com.example.app.model.User;
import com.example.app.repositories.ReservationRepository;
import com.example.app.room_reservation.TestFixtures;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test-db")
public class ReservationRepositoryTest {


    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ReservationRepository reservationRepository;


    // This unit test verifies whether the repository correctly handles reservation save and retrieve operations.
    @Test
    public void saveAndFindReservation() {
        Room room = TestFixtures.room();
        testEntityManager.persist(room);

        User user = TestFixtures.user();
        testEntityManager.persist(user);

        Reservation reservation = TestFixtures.reservation(room, user);
        // Saves the reservation object to the database and immediately performs a flush, meaning that changes are propagated to the database.
        testEntityManager.persistAndFlush(reservation);

        Reservation foundReservation = reservationRepository.findById(reservation.getId()).orElse(null);

        assertNotNull(foundReservation);
        assertEquals(reservation.getRoom(), foundReservation.getRoom());
        assertEquals(reservation.getReservationStartDate(), foundReservation.getReservationStartDate());
        assertEquals(reservation.getReservationEndDate(), foundReservation.getReservationEndDate());
        assertEquals(reservation.getUser(), foundReservation.getUser());
        assertEquals(reservation.getReservationDate(), foundReservation.getReservationDate());
        assertEquals(reservation.getReservationStatus(), foundReservation.getReservationStatus());
    }

    @Test
    public void findAllByUser() {
        User user = TestFixtures.user();
        testEntityManager.persist(user);

        Room room1 = TestFixtures.room();
        Room room2 = TestFixtures.room();

        testEntityManager.persistAndFlush(room1);
        testEntityManager.persistAndFlush(room2);

        Reservation reservation1 = TestFixtures.reservation(room1, user);
        Reservation reservation2 = TestFixtures.reservation(room2, user);

        // Save Room objects before saving reservations to ensure unique Room objects are already in the database.
        testEntityManager.persistAndFlush(reservation1);
        testEntityManager.persistAndFlush(reservation2);

        List<Reservation> reservations = reservationRepository.findAllByUser(user);

        // Check if the list of reservations is not empty.
        assertFalse(reservations.isEmpty());

        // Check if the attributes of individual reservations in the list match expectations.

        Reservation foundReservation1 = reservations.get(0);
        assertEquals(reservation1.getReservationStartDate(), foundReservation1.getReservationStartDate());
        assertEquals(reservation1.getReservationEndDate(), foundReservation1.getReservationEndDate());
        assertEquals(reservation1.getRoom(), foundReservation1.getRoom());
        assertEquals(reservation1.getUser(), foundReservation1.getUser());

        Reservation foundReservation2 = reservations.get(1);
        assertEquals(reservation2.getReservationStartDate(), foundReservation2.getReservationStartDate());
        assertEquals(reservation2.getReservationEndDate(), foundReservation2.getReservationEndDate());
        assertEquals(reservation2.getRoom(), foundReservation2.getRoom());
        assertEquals(reservation2.getUser(), foundReservation2.getUser());
    }

    @Test
    public void findOverlappingReservations() {
        Room room = TestFixtures.room();
        testEntityManager.persist(room);

        User user = TestFixtures.user();
        testEntityManager.persist(user);

        // Create a base reservation
        Reservation reservation1 = TestFixtures.reservation(room, user);
        testEntityManager.persistAndFlush(reservation1);

        // Create an overlapping reservation on the base reservation
        Reservation overlappingReservation = TestFixtures.reservation(room, user);
        overlappingReservation.setReservationStartDate(reservation1.getReservationStartDate().minusDays(1));
        overlappingReservation.setReservationEndDate(reservation1.getReservationEndDate().plusDays(1));
        testEntityManager.persistAndFlush(overlappingReservation);

        // Create a reservation that starts and ends before the given reservation
        Reservation nonOverlappingReservation1 = TestFixtures.reservation(room, user);
        nonOverlappingReservation1.setReservationStartDate(reservation1.getReservationStartDate().minusDays(3));
        nonOverlappingReservation1.setReservationEndDate(reservation1.getReservationStartDate().minusDays(2));
        testEntityManager.persistAndFlush(nonOverlappingReservation1);

        // Create a reservation that starts after the given reservation
        Reservation nonOverlappingReservation2 = TestFixtures.reservation(room, user);
        nonOverlappingReservation2.setReservationStartDate(reservation1.getReservationEndDate().plusDays(2));
        nonOverlappingReservation2.setReservationEndDate(reservation1.getReservationEndDate().plusDays(3));
        testEntityManager.persistAndFlush(nonOverlappingReservation2);

        // Check if the findOverlappingReservations method returns the overlapping reservation
        List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(
                room, reservation1.getReservationStartDate(),
                reservation1.getReservationEndDate(), reservation1.getId());

        assertEquals(1, overlappingReservations.size());
        assertEquals(overlappingReservation.getId(), overlappingReservations.get(0).getId());

        // Check if the list of overlapping reservations is empty for nonOverlappingReservation1
        List<Reservation> nonOverlappingReservations1 = reservationRepository.findOverlappingReservations(
                room, nonOverlappingReservation1.getReservationStartDate(),
                nonOverlappingReservation1.getReservationEndDate(), nonOverlappingReservation1.getId());

        assertEquals(0, nonOverlappingReservations1.size());

        // Check if the list of overlapping reservations is empty for nonOverlappingReservation2
        List<Reservation> nonOverlappingReservations2 = reservationRepository.findOverlappingReservations(
                room, nonOverlappingReservation2.getReservationStartDate(),
                nonOverlappingReservation2.getReservationEndDate(), nonOverlappingReservation2.getId());

        assertEquals(0, nonOverlappingReservations2.size());
    }

    @Test
    public void findAllByReservationStatus() {
        Room room = TestFixtures.room();
        testEntityManager.persist(room);

        User user = TestFixtures.user();
        testEntityManager.persist(user);

        // Create several reservations with different statuses
        Reservation activeReservation1 = TestFixtures.reservation(room, user);
        testEntityManager.persistAndFlush(activeReservation1);
        activeReservation1.setReservationStatus("active");

        Reservation finishedReservation = TestFixtures.reservation(room, user);
        testEntityManager.persistAndFlush(finishedReservation);
        finishedReservation.setReservationStatus("finished");

        Reservation activeReservation2 = TestFixtures.reservation(room, user);
        testEntityManager.persistAndFlush(activeReservation2);
        activeReservation2.setReservationStatus("active");

        reservationRepository.saveAll(List.of(activeReservation1, finishedReservation, activeReservation2));

        // Check if the findAllByReservationStatus method returns reservations with a given status
        List<Reservation> activeReservations = reservationRepository.findAllByReservationStatus("active");
        assertEquals(2, activeReservations.size());

        List<Reservation> finishedReservations = reservationRepository.findAllByReservationStatus("finished");
        assertEquals(1, finishedReservations.size());
    }
}
