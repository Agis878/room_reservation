package com.example.app.service;

import com.example.app.model.Reservation;
import com.example.app.model.Room;
import com.example.app.model.User;
import com.example.app.repositories.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository mockReservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    void testFindAll() {
        Room room = Room.builder().id(1L).seatsQty(43).name("Room1").build();
        Reservation reservation1 = Reservation.builder().id(1L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(1)).reservationEndDate(LocalDate.now().plusDays(2)).reservationStatus("active").room(room).build();
        Reservation reservation2 = Reservation.builder().id(2L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(3)).reservationEndDate(LocalDate.now().plusDays(4)).reservationStatus("active").room(room).build();

        List<Reservation> reservations = Arrays.asList(reservation1, reservation2);

        when(mockReservationRepository.findAll()).thenReturn(reservations);

        List<Reservation> result = reservationService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(reservation1));
        assertTrue(result.contains(reservation2));
    }

    @Test
    void testFindAllByReservationStatus() {
        Room room = Room.builder().id(1L).seatsQty(43).name("Room1").build();
        Reservation reservation1 = Reservation.builder().id(1L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(1)).reservationEndDate(LocalDate.now().plusDays(2)).reservationStatus("active").room(room).build();
        Reservation reservation2 = Reservation.builder().id(2L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(3)).reservationEndDate(LocalDate.now().plusDays(4)).reservationStatus("active").room(room).build();

        Reservation reservation3 = Reservation.builder().id(2L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().minusDays(3)).reservationEndDate(LocalDate.now().minusDays(2)).reservationStatus("finished").room(room).build();

        List<Reservation> activeReservations = Arrays.asList(reservation1, reservation2);
        List<Reservation> finishedReservations = Collections.singletonList(reservation3);

        when(mockReservationRepository.findAllByReservationStatus("active")).thenReturn(activeReservations);
        when(mockReservationRepository.findAllByReservationStatus("finished")).thenReturn(finishedReservations);


        List<Reservation> activeResult = reservationService.findAllByReservationStatus("active");
        List<Reservation> finishedResult = reservationService.findAllByReservationStatus("finished");
        List<Reservation> allResult = reservationService.findAllByReservationStatus("other");

        assertEquals(2, activeResult.size());
        assertEquals(1, finishedResult.size());
        assertEquals(0, allResult.size());
        assertTrue(activeResult.containsAll(Arrays.asList(reservation1, reservation2)));
        assertTrue(finishedResult.contains(reservation3));
    }

    @Test
    void testAddReservation() {
        Room room = Room.builder().id(1L).seatsQty(43).name("Room1").build();

        Reservation reservation = Reservation.builder().id(1L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(1)).reservationEndDate(LocalDate.now().plusDays(2)).reservationStatus("active").room(room).build();

        boolean result = reservationService.addReservation(reservation);

        verify(mockReservationRepository).save(reservation);
        assertTrue(result);

    }

    @Test
    public void testValidReservation() {

        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(7);
        LocalDate pastDate = currentDate.minusDays(7);


        Reservation validReservation = Reservation.builder().id(1L).reservationDate(currentDate).reservationStartDate(futureDate).reservationEndDate(futureDate.plusDays(2)).build();

        Reservation invalidStartDate = Reservation.builder().id(2L).reservationDate(currentDate).reservationStartDate(pastDate).reservationEndDate(futureDate).build();
        Reservation invalidEndDate = Reservation.builder().id(2L).reservationDate(currentDate).reservationStartDate(futureDate).reservationEndDate(currentDate).build();

        assertTrue(reservationService.isValidReservation(validReservation));
        assertFalse(reservationService.isValidReservation(invalidStartDate));
        assertFalse(reservationService.isValidReservation(invalidEndDate));
    }

    @Test
    void testIsRoomAvailable() {
        Room room = Room.builder().id(1L).seatsQty(43).name("Room1").build();
        Reservation reservation1 = Reservation.builder().id(1L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(1)).reservationEndDate(LocalDate.now().plusDays(3)).reservationStatus("active").room(room).build();
        Reservation reservation2 = Reservation.builder().id(2L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(2)).reservationEndDate(LocalDate.now().plusDays(4)).reservationStatus("active").room(room).build();

        Mockito.doReturn(Collections.singletonList(reservation1)).when(mockReservationRepository).findOverlappingReservations(reservation2.getRoom(), reservation2.getReservationStartDate(), reservation2.getReservationEndDate(), reservation2.getId());
        assertFalse(reservationService.isRoomAvailable(reservation2));
    }

    @Test
    void testFindById() {
        Room room = Room.builder().id(1L).seatsQty(43).name("Room1").build();
        Reservation reservation = Reservation.builder().id(1L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(1)).reservationEndDate(LocalDate.now().plusDays(3)).reservationStatus("active").room(room).build();

        when(mockReservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(mockReservationRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Reservation> foundReservation1 = reservationService.findById(1L);
        Optional<Reservation> foundReservation2 = reservationService.findById(2L);

        assertTrue(foundReservation1.isPresent());
        assertEquals(reservation, foundReservation1.get());

        assertTrue(foundReservation2.isEmpty());
    }

    @Test
    public void testDeleteReservation() {
        Long reservationIdToDelete = 1L;

        reservationService.deleteReservation(reservationIdToDelete);

        verify(mockReservationRepository, times(1)).deleteById(reservationIdToDelete);
    }

    @Test
    void testUpdateReservationStatus() {
        Room room = Room.builder().id(1L).seatsQty(43).name("Room1").build();

        // Create reservations with future end dates
        Reservation reservation1 = Reservation.builder().id(1L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(1)).reservationEndDate(LocalDate.now().plusDays(3)).reservationStatus("finished").room(room).build();
        // Create reservations with past end dates
        Reservation reservation2 = Reservation.builder().id(2L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().minusDays(2)).reservationEndDate(LocalDate.now().minusDays(1)).reservationStatus("active").room(room).build();

        when(mockReservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));

        reservationService.updateReservationStatus();

        verify(mockReservationRepository, times(1)).saveAll(anyList());

        assertEquals("active", reservation1.getReservationStatus());
        assertEquals("finished", reservation2.getReservationStatus());
    }

    @Test
    void testFindAllByUserWhenUserHaveOneReservation() {
        User userWithOneReservation = User.builder().id(1L).build();

        Reservation reservation1 = Reservation.builder().id(1L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(1)).reservationEndDate(LocalDate.now().plusDays(2)).reservationStatus("active").user(userWithOneReservation).build();

        when(mockReservationRepository.findAllByUser(userWithOneReservation)).thenReturn(List.of(reservation1));
        List<Reservation> userReservations1 = reservationService.findAllByUser(userWithOneReservation);

        assertEquals(1, userReservations1.size());
        assertTrue(userReservations1.contains(reservation1));
    }

    @Test
    void testFindAllByUserWhenUserHaveMoreThanOneReservation() {
        User userWithTwoReservations = User.builder().id(2L).build();

        Reservation reservation2 = Reservation.builder().id(2L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(3)).reservationEndDate(LocalDate.now().plusDays(4)).reservationStatus("active").user(userWithTwoReservations).build();
        Reservation reservation3 = Reservation.builder().id(3L).reservationDate(LocalDate.now()).reservationStartDate(LocalDate.now().plusDays(5)).reservationEndDate(LocalDate.now().plusDays(6)).reservationStatus("active").user(userWithTwoReservations).build();

        when(mockReservationRepository.findAllByUser(userWithTwoReservations)).thenReturn(List.of(reservation2, reservation3));
        List<Reservation> userReservations2 = reservationService.findAllByUser(userWithTwoReservations);

        assertEquals(2, userReservations2.size());
        assertTrue(userReservations2.contains(reservation2));
        assertTrue(userReservations2.contains(reservation3));
    }

    @Test
    void testFindAllByUserWithoutReservation() {
        User userWithoutReservations = User.builder().id(3L).build();

        List<Reservation> userReservations3 = reservationService.findAllByUser(userWithoutReservations);

        assertEquals(0, userReservations3.size());
        assertTrue(userReservations3.isEmpty());
    }
}
