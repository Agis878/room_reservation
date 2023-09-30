package com.example.app.controller;

import com.example.app.model.Reservation;
import com.example.app.model.User;

import com.example.app.service.ReservationService;
import com.example.app.service.RoomService;
import com.example.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


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
    public String getUserView(@SessionAttribute("loggedUser") User loggedUser, Model model) {
        model.addAttribute("reservationList",reservationService.findAllByUser(loggedUser));
        return "user-view";
    }

    @GetMapping("/add")
    public String addReservationForm(@SessionAttribute("loggedUser") User loggedUser, Model model) {

        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("rooms", roomService.findAll());

        return "/reservation-form";

    }

    @PostMapping("/add")
    public String addReservation(Reservation reservation, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("rooms", roomService.findAll());
            return "/reservation-form";
        }

        reservationService.addReservation(reservation);
        System.out.println("dodano rezervarcj");
        return "redirect:/user";
    }



}
