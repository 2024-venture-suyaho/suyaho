package com.venture.suyaho.repository;

import com.venture.suyaho.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
