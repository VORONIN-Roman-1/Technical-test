package com.voronin.technicaltest.service;

import com.voronin.technicaltest.dao.UserRepository;
import com.voronin.technicaltest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserServiceImp implements  UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public User findById(long userId) {
       return userRepository.findById(userId).orElseThrow(()->new RuntimeException());
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
