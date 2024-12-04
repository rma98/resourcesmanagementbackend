package br.edu.ifpe.manager.service;

import br.edu.ifpe.manager.model.Reservation;
import br.edu.ifpe.manager.repository.ReservationRepository;
import br.edu.ifpe.manager.repository.ResourceRepository;
import br.edu.ifpe.manager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    private ReservationService reservationService;
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private ResourceRepository resourceRepository;

    @BeforeEach
    void setUp() {
        reservationRepository = mock(ReservationRepository.class);
        userRepository = mock(UserRepository.class);
        resourceRepository = mock(ResourceRepository.class);

        // Pass all dependencies to the ReservationService constructor
        reservationService = new ReservationService(
            reservationRepository,
            userRepository,
            resourceRepository
        );
    }

    @Test
    void testMakeReservation() {
        // Arrange
        Long userId = 1L;
        Long resourceId = 1L;
        LocalDateTime startDate = LocalDateTime.of(2023, 12, 5, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 12, 5, 12, 0);
        String additionalResource = "Projector";

        var user = new br.edu.ifpe.manager.model.User();
        user.setId(userId);

        var resource = new br.edu.ifpe.manager.model.Resource();
        resource.setId(resourceId);

        var reservation = new Reservation();
        reservation.setId(1L);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));
        when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(reservation);

        // Act
        Reservation result = reservationService.makeReservation(userId, resourceId, startDate, endDate, additionalResource);

        // Assert
        assertNotNull(result);
        verify(reservationRepository, times(1)).save(Mockito.any(Reservation.class));
    }
}
