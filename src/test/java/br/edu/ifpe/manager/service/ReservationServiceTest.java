package br.edu.ifpe.manager.service;

import br.edu.ifpe.manager.model.Reservation;
import br.edu.ifpe.manager.model.Resource;
import br.edu.ifpe.manager.model.ResourceStatus;
import br.edu.ifpe.manager.model.User;
import br.edu.ifpe.manager.repository.ReservationRepository;
import br.edu.ifpe.manager.repository.ResourceRepository;
import br.edu.ifpe.manager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReservationService reservationService;

    private User user;
    private Resource resource;

    @BeforeEach
    public void setUp() {
        user = User.builder().name("Robson").email("robson@example.com").password("password123").build();
        resource = Resource.builder().name("Room 101").description("A spacious room").capacity(10).location("Building A").status(ResourceStatus.AVAILABLE).build();
    }

    @Test
    public void testMakeReservation() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(resourceRepository.findById(1L)).thenReturn(Optional.of(resource));

        LocalDateTime start = LocalDateTime.now().plusHours(1);
        LocalDateTime end = start.plusHours(2);

        Reservation reservation = reservationService.makeReservation(1L, 1L, start, end, "Projector");

        assertNotNull(reservation);
        assertEquals("Robson", reservation.getUser().getName());
        assertEquals("Room 101", reservation.getResource().getName());
    }
}
