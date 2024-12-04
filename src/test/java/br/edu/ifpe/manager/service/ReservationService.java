package br.edu.ifpe.manager.service;

import br.edu.ifpe.manager.model.Reservation;
import br.edu.ifpe.manager.model.Resource;
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

    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private ResourceRepository resourceRepository;
    
    @Autowired
    private UserRepository userRepository;

    // User makes a reservation
    public Reservation makeReservation(Long userId, Long resourceId, LocalDateTime startDate, LocalDateTime endDate, String additionalResource) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Resource> resource = resourceRepository.findById(resourceId);

        if (user.isPresent() && resource.isPresent()) {
            if (startDate.isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Start date cannot be in the past.");
            }

            // Check if the resource is available during the requested time frame
            List<Reservation> existingReservations = reservationRepository.findByResourceId(resourceId);
            for (Reservation existing : existingReservations) {
                if (!(endDate.isBefore(existing.getStartDate()) || startDate.isAfter(existing.getEndDate()))) {
                    throw new IllegalArgumentException("Resource is already reserved during this time.");
                }
            }

            Reservation reservation = Reservation.builder()
                    .user(user.get())
                    .resource(resource.get())
                    .startDate(startDate)
                    .endDate(endDate)
                    .additionalResource(additionalResource)
                    .build();

            return reservationRepository.save(reservation);
        } else {
            throw new IllegalArgumentException("User or Resource not found.");
        }
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
