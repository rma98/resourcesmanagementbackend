package br.edu.ifpe.manager.repository;

import br.edu.ifpe.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Method to find a user by email
    Optional<User> findByEmail(String email);

    // Method to check if a user exists by email
    boolean existsByEmail(String email);
}
