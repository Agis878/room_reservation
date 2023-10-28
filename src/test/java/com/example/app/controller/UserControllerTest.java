package com.example.app.controller;

import com.example.app.model.Reservation;
import com.example.app.model.Room;
import com.example.app.model.User;
import com.example.app.room_reservation.TestFixtures;
import com.example.app.service.ReservationService;
import com.example.app.service.RoomService;
import com.example.app.service.UserService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private RoomService roomService;
    @MockBean
    private ReservationService reservationService;
    @Captor
    private ArgumentCaptor<Reservation> reservationCaptor;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserView_ShouldReturnUserViewWithHisReservations() throws Exception {
        Room room = TestFixtures.room();
        User user = TestFixtures.user();
        List<Reservation> reservations = List.of(TestFixtures.reservation(1L, user, room), TestFixtures.reservation(2L, user, room), TestFixtures.reservation(3L, user, room));
        user.setReservations(reservations);

        Mockito.when(userService.getUserWithReservationsByUserName(Mockito.anyString())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/user").with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER"))).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("user/user-view")).andExpect(MockMvcResultMatchers.model().attributeExists("loggedUser")).andExpect(MockMvcResultMatchers.model().attribute("reservationList", Matchers.is(reservations)));
    }

    @Test
    void shouldPrepareViewToCreateNewReservation() throws Exception {
        User user = TestFixtures.user();
        List<Room> rooms = List.of(TestFixtures.room(), TestFixtures.room());

        Mockito.when(userService.getUserWithReservationsByUserName(Mockito.anyString())).thenReturn(user);
        Mockito.when(roomService.findAll()).thenReturn(rooms);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/add").with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER"))).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("reservation/reservation-add")).andExpect(MockMvcResultMatchers.model().attributeExists("loggedUser")).andExpect(MockMvcResultMatchers.model().attribute("rooms", Matchers.is(rooms))).andExpect(MockMvcResultMatchers.model().attribute("reservation", Matchers.allOf(Matchers.instanceOf(Reservation.class), Matchers.hasProperty("id", Matchers.nullValue()))));
    }

    @Test
    public void shouldPrepareViewWithErrorsWhenNewReservationIsInvalid() throws Exception {
        Reservation invalidReservation = Reservation.builder().reservationStartDate(LocalDate.now().minusDays(3)).reservationEndDate(LocalDate.now().minusDays(5)).build();
        mockMvc.perform(MockMvcRequestBuilders.post("/user/add").param("reservationStartDate", invalidReservation.getReservationStartDate().toString()).param("reservationEndDate", invalidReservation.getReservationEndDate().toString()).with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")).with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("reservation/reservation-add")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("reservation", "reservationStartDate", "reservationEndDate")).andExpect(MockMvcResultMatchers.model().attribute("rooms", Matchers.notNullValue())).andExpect(MockMvcResultMatchers.model().attribute("errors", Matchers.not(Matchers.empty())));
    }

    @Test
    public void shouldPrepareViewToDeleteReservation() throws Exception {
        Long id = 1L;
        Reservation reservation = TestFixtures.reservation(id);
        Mockito.when(reservationService.findById(id)).thenReturn(Optional.of(reservation));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/delete").param("id", String.valueOf(id)).with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER"))).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("reservation/reservation-delete")).andExpect(MockMvcResultMatchers.model().attribute("reservation", Matchers.is(reservation)));
    }

    @Test
    public void shouldDeleteReservation() throws Exception {
        Long id = 1L;
        Reservation reservation = TestFixtures.reservation(id);
        Mockito.when(reservationService.findById(1L)).thenReturn(Optional.of(reservation));
        Mockito.doNothing().when(reservationService).deleteReservation(ArgumentMatchers.eq(id));

        mockMvc.perform(MockMvcRequestBuilders.post("/user/delete").param("id", String.valueOf(1L)).with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")).with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.redirectedUrl("/user"));

        Mockito.verify(reservationService, Mockito.times(1)).deleteReservation(ArgumentMatchers.eq(1L));
    }

    @Test
    public void shouldPrepareViewToUpdateReservation() throws Exception {
        User user = TestFixtures.user();
        Long id = 1L;

        Reservation reservation = TestFixtures.reservation(id);
        Mockito.when(reservationService.findById(id)).thenReturn(Optional.of(reservation));
        Mockito.when(userService.getUserWithReservationsByUserName(Mockito.anyString())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/update").param("id", String.valueOf(id)).with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER"))).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("reservation/reservation-update")).andExpect(MockMvcResultMatchers.model().attributeExists("rooms")).andExpect(MockMvcResultMatchers.request().sessionAttribute("SPRING_SECURITY_CONTEXT", Matchers.notNullValue())).andExpect(MockMvcResultMatchers.model().attribute("reservation", Matchers.is(Optional.of(reservation))));
    }


    @Test
    public void shouldPrepareViewWithErrorsWhenUpdatedReservationIsInvalid() throws Exception {
        Reservation invalidReservation = Reservation.builder().reservationStartDate(LocalDate.now().minusDays(3)).reservationEndDate(LocalDate.now().minusDays(5)).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/user/update").param("reservationStartDate", invalidReservation.getReservationStartDate().toString()).param("reservationEndDate", invalidReservation.getReservationEndDate().toString()).with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")).with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.view().name("reservation/reservation-update")).andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors("reservation", "reservationStartDate", "reservationEndDate")).andExpect(MockMvcResultMatchers.model().attribute("rooms", Matchers.notNullValue())).andExpect(MockMvcResultMatchers.model().attribute("errors", Matchers.not(Matchers.empty())));
    }

    @Test
    public void shouldUpdateReservation() throws Exception {
        Reservation reservation = TestFixtures.reservation(1L);
        Mockito.when(reservationService.addReservation(reservationCaptor.capture())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/update").param("reservationStartDate", reservation.getReservationStartDate().toString()).param("reservationEndDate", reservation.getReservationEndDate().toString()).with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER")).with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(MockMvcResultMatchers.redirectedUrl("/user"));

        Reservation updateReservation = reservationCaptor.getValue();
        MatcherAssert.assertThat(updateReservation, Matchers.allOf(Matchers.hasProperty("reservationStartDate", Matchers.equalTo(reservation.getReservationStartDate())), Matchers.hasProperty("reservationEndDate", Matchers.equalTo(reservation.getReservationEndDate()))));
    }
}