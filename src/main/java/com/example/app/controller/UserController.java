package com.example.app.controller;

import com.example.app.model.Reservation;
import com.example.app.repositories.ReservationRepository;
import com.example.app.repositories.RoomRepository;
import com.example.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/user")
public class UserController {
    private final UserService userService;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;


    public UserController(UserService userService, RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.userService = userService;
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public String getUserView() {
        return "user-view";
    }

    @GetMapping("add")
    public String addReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("rooms", roomRepository.findAll());
        return "reservation-form";

    }
    @PostMapping("/add")
    public String addReservation(Reservation reservation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
    return "/reservation-form";
        }
        reservationRepository.save(reservation);
        System.out.println("dodano rezervarcj");
        return "redirect:/login/user";
    }

}
