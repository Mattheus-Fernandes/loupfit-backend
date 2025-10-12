package com.loupfituserservice.userservice.infrastructure.repository;

import com.loupfituserservice.userservice.infrastructure.entity.User;
import com.loupfituserservice.userservice.infrastructure.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
