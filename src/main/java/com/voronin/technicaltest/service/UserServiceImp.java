package com.voronin.technicaltest.service;

import com.voronin.technicaltest.dao.UserRepository;
import com.voronin.technicaltest.entity.User;
import com.voronin.technicaltest.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found on : " + userId));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findFirstByUserName(userName);
    }
}
