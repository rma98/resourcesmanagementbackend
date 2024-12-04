package br.edu.ifpe.manager.service;

import br.edu.ifpe.manager.model.User;
import br.edu.ifpe.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	// Register a new user
	public User registerUser(User user) {
        // Check if a user with the same email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already taken");
        }
        // Save the user if the email is available
        return userRepository.save(user);
    }

	// Fetch user details by ID
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	// Fetch a user by email (for authentication purposes, if needed)
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	// Check if user exists by email
	public boolean userExistsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
}
