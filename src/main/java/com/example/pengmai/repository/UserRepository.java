package com.example.pengmai.repository;

import com.example.pengmai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findUserByUserId(String userId);
    User findUserByUsername(String username);
}
