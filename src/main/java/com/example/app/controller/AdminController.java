package com.example.app.controller;

import com.example.app.model.Reservation;
import com.example.app.model.User;
import com.example.app.service.ReservationService;
import com.example.app.service.RoomService;
import com.example.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ReservationService reservationService;
    private final RoomService roomService;


    public AdminController(UserService userService, ReservationService reservationService, RoomService roomService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.roomService = roomService;
    }

    @GetMapping
    public String getAdminView(Model model) {
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("roomList", roomService.findAll());
        model.addAttribute("reservationList", reservationService.findAll());
        return "admin/admin-view";
    }

    @GetMapping("/report_1")
    public String getReportByReservationStatus(Model model, @RequestParam(defaultValue = "all") String reservationType) {
        List<Reservation> reservationsByStatus = reservationService.findAllByReservationStatus(reservationType);
        model.addAttribute("reservations", reservationsByStatus);
        model.addAttribute("noReservationsMessage", reservationService.findAllByReservationStatus(reservationType).isEmpty() ? "No reservations available" : null);
        return "admin/admin-report-reservation-status";
    }

    @GetMapping("/report_2")
    public String findUsers(@RequestParam(name = "userSelectionType") String userSelectionType, @RequestParam(name = "selectedUsersId") Long selectedUserId, Model model) {
        model.addAttribute("userList", userService.findAll());
        if (userSelectionType.equals("all")) {
            model.addAttribute("reservations", reservationService.findAll());
            model.addAttribute("noReservationsMessage", reservationService.findAll().isEmpty() ? "No reservations available" : null);
        } else {
            User selectedUser = userService.findById(selectedUserId).orElse(null);
            List<Reservation> reservationsForCurrentUser = reservationService.findAllByUser(selectedUser);
            model.addAttribute("reservationsForCurrentUser", reservationsForCurrentUser);
            model.addAttribute("noReservationsMessageCurrentUser", reservationsForCurrentUser.isEmpty() ? "No reservations for the current user" : null);
        }
        return "admin/admin-report-users";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }
}



