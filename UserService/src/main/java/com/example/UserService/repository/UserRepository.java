package com.example.UserService.repository;

import com.example.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User deleteByUserId(String userId);
}
