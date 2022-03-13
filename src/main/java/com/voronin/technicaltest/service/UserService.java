package com.voronin.technicaltest.service;

import com.voronin.technicaltest.entity.User;

public interface UserService {
    public User findById(long userId);
    public void save (User user);
}
