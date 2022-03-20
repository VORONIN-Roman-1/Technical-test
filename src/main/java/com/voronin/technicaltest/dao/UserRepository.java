package com.voronin.technicaltest.dao;

import com.voronin.technicaltest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface extends {@link JpaRepository}
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * search a {@link User} by Username
     *
     * @param userName Name of the user
     * @return {@link java.util.Optional} of the {@link User}
     */
    Optional<User> findByUserName(String userName);
}
