package br.edu.ifpe.manager.service;

import br.edu.ifpe.manager.model.User;
import br.edu.ifpe.manager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .name("Robson")
                .email("robson@example.com")
                .password("password123")
                .build();
    }

    @Test
    public void testRegisterUser() {
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.registerUser(user);

        assertNotNull(savedUser);
        assertEquals("Robson", savedUser.getName());
        assertEquals("robson@example.com", savedUser.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserByEmail() {
        when(userRepository.findByEmail("robson@example.com")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserByEmail("robson@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("Robson", foundUser.get().getName());
    }
}
