package com.voronin.technicaltest.service;

import com.voronin.technicaltest.dao.UserRepository;
import com.voronin.technicaltest.entity.User;
import com.voronin.technicaltest.exception.ResourceNotFoundException;
import com.voronin.technicaltest.exception.UserForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Value("${user.ageAuthorization}")
    private int ageAuthorization;
    @Value("${user.listOfCountriesAuthorization}")
    private Set<String> listOfCountries;

    UserRepository userRepository;

    public UserServiceImp(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        int age = Period.between(user.getBirthDate(), LocalDate.now()).getYears();
        if (userRepository.existsById(user.getUserName()))
            throw new UserForbiddenException("User" + user.getUserName() + " already exists");
        if (age < ageAuthorization)
            throw new UserForbiddenException("User is not adult: " + age + " year(s) old but necessary: " + ageAuthorization);
        if (!listOfCountries.contains(user.getCountry()))
            throw new UserForbiddenException("Service unavailable in this country " + user.getCountry() + ". Available countries : " + listOfCountries);
        return userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName).orElseThrow(() -> new ResourceNotFoundException("User " + userName + " not found"));
    }
}
