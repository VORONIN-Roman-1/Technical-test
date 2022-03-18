package com.voronin.technicaltest.service;

import com.voronin.technicaltest.dao.UserRepository;
import com.voronin.technicaltest.entity.User;
import com.voronin.technicaltest.exception.ResourceNotFoundException;
import com.voronin.technicaltest.exception.UserConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private Environment environment;

    private UserRepository userRepository;

    public UserServiceImp(@Autowired Environment environment, @Autowired UserRepository userRepository) {
        this.environment = environment;
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        int age = Period.between(user.getBirthDate(), LocalDate.now()).getYears();
        if (userRepository.existsById(user.getUserName()))
            throw new UserConflictException("User" + user.getUserName() + " already exists");

        int ageAuthorization = environment.getProperty("user.ageAuthorization", Integer.class, 18);
        if (age < ageAuthorization)
            throw new UserConflictException("User is not adult: " + age + " year(s) old but necessary: " + ageAuthorization);

        Set<String> listOfCountries = environment.getProperty("user.listOfCountriesAuthorization", Set.class, new HashSet<String>());
        if (!listOfCountries.contains(user.getCountry()))
            throw new UserConflictException("Service unavailable in this country " + user.getCountry() + ". Available countries : " + listOfCountries);
        return userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundException("User " + userName + " not found"));
    }
}
