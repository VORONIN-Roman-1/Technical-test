package com.voronin.technicaltest.service;

import com.voronin.technicaltest.entity.User;

public interface UserService {
    User findById(long userId);

    User save(User user);

    User findByUserName(String userName);
}
