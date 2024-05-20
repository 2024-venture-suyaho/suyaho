package com.venture.suyaho.deal.service;


import com.venture.suyaho.deal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    User findByUsername(String username);
}