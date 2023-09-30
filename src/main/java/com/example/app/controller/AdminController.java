package com.example.app.controller;

import com.example.app.model.Reservation;
import com.example.app.service.ReservationService;
import com.example.app.service.RoomService;
import com.example.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.ManyToOne;
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
        model.addAttribute("reservationList",reservationService.findAll());
       return "/admin-view";
    }

    @GetMapping("/report_1")
    public String getReportByReservationStatus(Model model, @RequestParam String reservationType) {
        // reservationService.findAllByReservationStatus(reservationType);

        if (reservationType.equals("Aktywny")) {
            List<Reservation> reservationsActive = reservationService.findAllByReservationStatus(reservationType);
            model.addAttribute("activeReservations", reservationsActive);
        } else if (reservationType.equals("Zakończony")) {
            List<Reservation> reservationsFinished = reservationService.findAllByReservationStatus(reservationType);
        model.addAttribute("finishedReservations", reservationsFinished);
        } else {
            List<Reservation> reservationsAll = reservationService.findAll();
            model.addAttribute("allReservations", reservationsAll);
        }

        return "/report-1";
    }
//    @PostMapping("/report_1")
//    public String getReportOneView();
//

}
