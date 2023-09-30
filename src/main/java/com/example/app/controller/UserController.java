package com.example.app.controller;

import com.example.app.model.Reservation;
import com.example.app.model.User;

import com.example.app.service.ReservationService;
import com.example.app.service.RoomService;
import com.example.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.awt.print.Book;
import java.util.Optional;


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

    @GetMapping("add")
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
            return "reservation-form";
        }

        reservationService.addReservation(reservation);
        System.out.println("dodano rezervarcj");
        return "redirect:/user";
    }

    @GetMapping("/delete")
    public String getDeleteView(Model model, @RequestParam Long id) {
        Optional<Reservation> reservationToDelete= reservationService.findById(id);
        if(reservationToDelete.isPresent()) {
            model.addAttribute("reservation", reservationToDelete.get());
            return "delete-view";
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
    public String getUpdateView(@SessionAttribute("loggedUser") User loggedUser, Model model, @RequestParam Long id) {
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("reservation", reservationService.findById(id));
        model.addAttribute("rooms", roomService.findAll());
        return "/update-view";
    }

    @PostMapping("/update")
    public String updateAuthor(Reservation reservation, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("rooms", roomService.findAll());
            return "/update-view";
        }
        reservationService.updateReservation(reservation);
        return "redirect:/user";
    }



}
