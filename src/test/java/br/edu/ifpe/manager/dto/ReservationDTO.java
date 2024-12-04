package br.edu.ifpe.manager.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDTO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long userId; // User ID for the reservation
    private Long resourceId; // Resource ID for the reservation
    private String additionalResource;
}
