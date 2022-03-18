package com.voronin.technicaltest.service;

import com.voronin.technicaltest.dao.UserRepository;
import com.voronin.technicaltest.entity.User;
import com.voronin.technicaltest.exception.ResourceNotFoundException;
import com.voronin.technicaltest.exception.UserConflictException;
import com.voronin.technicaltest.exception.UserRestrictedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserServiceImpTest {

    @Mock
    User user;

    @Mock
    Environment environment;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;

    @BeforeEach
    public void init() {
        when(environment.getProperty("user.ageAuthorization", Integer.class, 18)).thenReturn(18);
        when(environment.getProperty("user.listOfCountriesAuthorization", Set.class, new HashSet<String>())).thenReturn(Set.of("France"));

        when(user.getBirthDate()).thenReturn(LocalDate.of(1934, 8, 16));
        when(user.getUserName()).thenReturn("Pierre");
        when(user.getCountry()).thenReturn("France");

        when(userRepository.existsById("Pierre")).thenReturn(false);
    }

    @Test
    void saveCorrect() {
        userService.save(user);
        verify(userRepository, times(1)).existsById(anyString());
        verify(userRepository, times(1)).save(user);

    }

    @Test
    void saveIncorrectCountry() {
        when(user.getCountry()).thenReturn("ErrorCountry");
        Exception thrown = assertThrows(UserRestrictedException.class,
                () -> userService.save(user),
                "Expected save(user) to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("Service unavailable in this country"));
        verify(userRepository, times(0)).save(user);
    }

    @Test
    void saveIncorrectBirthDate() {
        when(user.getBirthDate()).thenReturn(LocalDate.of(2020, 2, 2));
        Exception thrown = assertThrows(UserRestrictedException.class,
                () -> userService.save(user),
                "Expected save(user) to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("User is not adult"));
        verify(userRepository, times(0)).save(user);
    }

    @Test
    void saveIncorrectUserExists() {
        when(userRepository.existsById(user.getUserName())).thenReturn(true);
        Exception thrown = assertThrows(UserConflictException.class,
                () -> userService.save(user),
                "Expected save(user) to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("already exists"));
        verify(userRepository, times(0)).save(user);
    }

    @Test
    void findByUserNameCorrect() {
        when(userRepository.findByUserName("Pierre")).thenReturn(Optional.of(user));
        User userTest = userService.findByUserName("Pierre");
        assertTrue(userTest.getCountry().contains("France"));
        verify(userRepository, times(1)).findByUserName(ArgumentMatchers.anyString());
    }

    @Test
    void findByUserNameIncorrect() {
        when(userRepository.findByUserName("Pierre")).thenReturn(Optional.empty());
        Exception thrown = assertThrows(ResourceNotFoundException.class,
                () -> userService.findByUserName("Pierre"),
                "Expected findByUserName(\"Pierre\") to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("not found"));
        verify(userRepository, times(1)).findByUserName(ArgumentMatchers.anyString());
    }
}
