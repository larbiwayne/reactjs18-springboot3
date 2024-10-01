package com.example.demo.user.repository;


import com.example.demo.user.model.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findUsersByRole(@Param("role") String role);
}

