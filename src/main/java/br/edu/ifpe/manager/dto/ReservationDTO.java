package br.edu.ifpe.manager.dto;

import br.edu.ifpe.manager.model.Reservation;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationDTO {
    private Long id;
    private Long userId;
    private Long resourceId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String additionalResource;

    // Convert from Reservation entity to ReservationDTO
    public static ReservationDTO fromEntity(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null");
        }

        return ReservationDTO.builder()
                .id(reservation.getId())
                .userId(reservation.getUser().getId())
                .resourceId(reservation.getResource().getId())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .additionalResource(reservation.getAdditionalResource())
                .build();
    }
}
