package com.voronin.technicaltest.service;

import com.voronin.technicaltest.dao.UserRepository;
import com.voronin.technicaltest.entity.User;
import com.voronin.technicaltest.exception.ResourceNotFoundException;
import com.voronin.technicaltest.exception.UserForbiddenException;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        Mockito.when(user.getBirthDate()).thenReturn(LocalDate.of(1934,8,16));
        Mockito.when(user.getUserName()).thenReturn("Pierre");
        Mockito.when(user.getCountry()).thenReturn("France");
    }

    @Test
    void saveCorrect() {
        userService.save(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void saveIncorrectCountry() {
        Mockito.when(user.getCountry()).thenReturn("ErrorCountry");
        Exception thrown = assertThrows(UserForbiddenException.class,
                () -> {
                    userService.save(user);
                },
                "Expected save(user) to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("Service unavailable in this country"));
        Mockito.verify(userRepository, Mockito.times(0)).save(user);
    }

    @Test
    void saveIncorrectBirthDate() {
        Mockito.when(user.getBirthDate()).thenReturn(LocalDate.of(2020,02,02));
        Exception thrown = assertThrows(UserForbiddenException.class,
                () -> {
                    userService.save(user);
                },
                "Expected save(user) to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("User is not adult"));
        Mockito.verify(userRepository, Mockito.times(0)).save(user);
    }

    @Test
    void findByIdCorrect() {
        Mockito.when(userRepository.findById(1l)).thenReturn(Optional.of(user));
        User userTest = userService.findById(1l);
        assertTrue(userTest.getUserName().contains("Pierre"));
        Mockito.verify(userRepository, Mockito.times(1)).findById(1l);
    }

    @Test
    void findByIdIncorrect() {
        Mockito.when(userRepository.findById(1l)).thenReturn(Optional.empty());
        Exception thrown = assertThrows(ResourceNotFoundException.class,
                () -> {
                    userService.findById(1l);
                },
                "Expected findById(1l) to throw, but it didn't");
        assertTrue(thrown.getMessage().contains("User not found "));
        Mockito.verify(userRepository, Mockito.times(1)).findById(1l);
    }
}