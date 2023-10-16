package com.example.app.controller;

import com.example.app.model.Reservation;
import com.example.app.model.User;
import com.example.app.service.ReservationService;
import com.example.app.service.RoomService;
import com.example.app.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoomService roomService;
    private final ReservationService reservationService;

    public UserController(UserService userService, RoomService roomService, ReservationService reservationService) {
        this.userService = userService;
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public String getUserView(@AuthenticationPrincipal UserDetails authenticatedUser, Model model) {

        User loggedUser = userService.getUserWithReservationsByUserName(authenticatedUser.getUsername());

        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("reservationList", loggedUser.getReservations());
        return "user/user-view";
    }


    @GetMapping("add")
    public String addReservationForm(@AuthenticationPrincipal UserDetails authenticatedUser, Model model) {

        User loggedUser = userService.getUserWithReservationsByUserName(authenticatedUser.getUsername());

        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("rooms", roomService.findAll());
        return "reservation/reservation-add";
    }


    @PostMapping("/add")
    public String addReservation(@Valid Reservation reservation, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            model.addAttribute("rooms", roomService.findAll());
            return "reservation/reservation-add";
        }
        // Attempt to add the reservation, handle conflicts
        boolean reservationAdded = reservationService.addReservation(reservation);
        if (reservationAdded) {
            return "redirect:/user";
        } else {
            model.addAttribute("rooms", roomService.findAll());
            model.addAttribute("error", "The reservation cannot be added due to a date conflict.");
            return "reservation/reservation-add";
        }
    }

    @GetMapping("/delete")
    public String getDeleteView(Model model, @RequestParam Long id) {
        Optional<Reservation> reservationToDelete = reservationService.findById(id);
        if (reservationToDelete.isPresent()) {
            model.addAttribute("reservation", reservationToDelete.get());
            return "reservation/reservation-delete";
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping("/delete")
    public String deleteReservation(@RequestParam Long id) {

        reservationService.deleteReservation(id);
        return "redirect:/user";
    }

    @GetMapping("/update")
    public String getUpdateView(@AuthenticationPrincipal UserDetails authenticatedUser, Model model, @RequestParam Long id) {
        User loggedUser = userService.getByUsername(authenticatedUser.getUsername());

        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("reservation", reservationService.findById(id));
        model.addAttribute("rooms", roomService.findAll());
        return "reservation/reservation-update";
    }

    @PostMapping("/update")
    public String updateReservation(@Valid Reservation reservation, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)));
            model.addAttribute("rooms", roomService.findAll());
            return "reservation/reservation-update";
        }
        /**
         * Attempt to update the reservation, handle conflicts
         */
        boolean reservationAdded = reservationService.addReservation(reservation);
        if (reservationAdded) {
            return "redirect:/user";
        } else {
            model.addAttribute("rooms", roomService.findAll());
            model.addAttribute("error", "The reservation cannot be added due to a date conflict.");
            return "reservation/reservation-update";
        }
    }

}
