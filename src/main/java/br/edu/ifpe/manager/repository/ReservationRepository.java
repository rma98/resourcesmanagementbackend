package br.edu.ifpe.manager.repository;

import br.edu.ifpe.manager.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId); // Find reservations by user ID
    List<Reservation> findByResourceId(Long resourceId); // Find reservations by resource ID
}
