package br.edu.ifpe.manager.controller;

import br.edu.ifpe.manager.model.Reservation;
import br.edu.ifpe.manager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // User makes a reservation
    @PostMapping
    public ResponseEntity<Reservation> makeReservation(@RequestParam Long userId,
                                                       @RequestParam Long resourceId,
                                                       @RequestParam String startDate,
                                                       @RequestParam String endDate,
                                                       @RequestParam(required = false) String additionalResource) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        Reservation reservation = reservationService.makeReservation(userId, resourceId, start, end, additionalResource);
        return ResponseEntity.ok(reservation);
    }
}
