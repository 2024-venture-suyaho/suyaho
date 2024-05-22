package com.venture.suyaho.repository;

import com.venture.suyaho.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u ")
    List<User> findAll();

    User findByUserName(String userName);
}
