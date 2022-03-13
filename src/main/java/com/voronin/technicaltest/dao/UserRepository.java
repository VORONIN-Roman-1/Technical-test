package com.voronin.technicaltest.dao;

import com.voronin.technicaltest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
