package com.venture.suyaho.repository;

import com.venture.suyaho.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String user_Email);
}
