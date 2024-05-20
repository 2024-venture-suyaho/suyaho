package com.venture.suyaho.repository;

import com.venture.suyaho.dto.UserDTO;
import com.venture.suyaho.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmailAndUserPwd(String userEmail, String userPwd);
}