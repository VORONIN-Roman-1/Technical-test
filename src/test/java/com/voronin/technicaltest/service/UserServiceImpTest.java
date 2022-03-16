package com.voronin.technicaltest.service;

import com.voronin.technicaltest.dao.UserRepository;
import com.voronin.technicaltest.entity.User;
import com.voronin.technicaltest.exception.ResourceNotFoundException;
import com.voronin.technicaltest.exception.UserForbiddenException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImpTest {
    @MockBean
    User user;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void init() {
        when(user.getBirthDate()).thenReturn(LocalDate.of(1934, 8, 16));
        when(user.getUserName()).thenReturn("Pierre");
        when(user.getCountry()).thenReturn("France");
    }

    @Test
    void saveCorrect() {
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void saveIncorrectCountry() {
        when(user.getCountry()).thenReturn("ErrorCountry");
        Exception thrown = assertThrows(UserForbiddenException.class,
                () -> {
                    userService.save(user);
                },
                "Expected save(user) to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("Service unavailable in this country"));
        verify(userRepository, times(0)).save(user);
    }

    @Test
    void saveIncorrectBirthDate() {
        when(user.getBirthDate()).thenReturn(LocalDate.of(2020, 02, 02));
        Exception thrown = assertThrows(UserForbiddenException.class,
                () -> {
                    userService.save(user);
                },
                "Expected save(user) to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("User is not adult"));
        verify(userRepository, times(0)).save(user);
    }

    @Test
    void saveIncorrectUserExists() {
        when(user.getBirthDate()).thenReturn(LocalDate.of(2020, 02, 02));
        Exception thrown = assertThrows(UserForbiddenException.class,
                () -> {
                    userService.save(user);
                },
                "Expected save(user) to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("User is not adult"));
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
                () -> {
                    userService.findByUserName("Pierre");
                },
                "Expected findByUserName(1l) to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("not found"));
        verify(userRepository, times(1)).findByUserName(ArgumentMatchers.anyString());
    }
}