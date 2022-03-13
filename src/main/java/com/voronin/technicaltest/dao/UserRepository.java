package com.voronin.technicaltest.dao;

import com.voronin.technicaltest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUserName(String userName);
}
