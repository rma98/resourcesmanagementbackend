package br.edu.ifpe.manager.service;

import br.edu.ifpe.manager.model.Reservation;
import br.edu.ifpe.manager.model.Resource;
import br.edu.ifpe.manager.model.ResourceStatus;
import br.edu.ifpe.manager.model.User;
import br.edu.ifpe.manager.repository.ReservationRepository;
import br.edu.ifpe.manager.repository.ResourceRepository;
import br.edu.ifpe.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;

    public ReservationService(
        ReservationRepository reservationRepository,
        UserRepository userRepository,
        ResourceRepository resourceRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.resourceRepository = resourceRepository;
    }

    public Reservation makeReservation(Long userId, Long resourceId, LocalDateTime startDate, LocalDateTime endDate, String additionalResource) {
        // Fetch user and resource
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new IllegalArgumentException("Resource not found"));

        // Create a reservation
        Reservation reservation = Reservation.builder()
                .user(user)
                .resource(resource)
                .startDate(startDate)
                .endDate(endDate)
                .additionalResource(additionalResource)
                .build();

        // Save and return the reservation
        return reservationRepository.save(reservation);
    }

    // Fetch all reservations for a user
    public List<Reservation> getUserReservations(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    // Fetch all reservations for a resource
    public List<Reservation> getResourceReservations(Long resourceId) {
        return reservationRepository.findByResourceId(resourceId);
    }
}
