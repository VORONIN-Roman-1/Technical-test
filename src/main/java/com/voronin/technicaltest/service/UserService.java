package com.voronin.technicaltest.service;

import com.voronin.technicaltest.entity.User;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Save user.
     *
     * @param user {@link User}
     * @return the {@link User}
     */
    User save(User user);

    /**
     * Find by user name {@link User}.
     *
     * @param userName the user name
     * @return the {@link User}
     */
    User findByUserName(String userName);

}
