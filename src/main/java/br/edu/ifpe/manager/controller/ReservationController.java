package br.edu.ifpe.manager.controller;

import br.edu.ifpe.manager.dto.ReservationDTO;
import br.edu.ifpe.manager.model.Reservation;
import br.edu.ifpe.manager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // User makes a reservation
    @PostMapping("/make")
    public ResponseEntity<ReservationDTO> makeReservation(
            @RequestParam Long userId,
            @RequestParam Long resourceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) String additionalResource) {

        Reservation reservation = reservationService.makeReservation(userId, resourceId, startDate, endDate, additionalResource);
        return ResponseEntity.ok(ReservationDTO.fromEntity(reservation));
    }
}
