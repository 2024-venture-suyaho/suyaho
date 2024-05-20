package com.venture.suyaho.service;

import com.venture.suyaho.domain.User;
import com.venture.suyaho.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAllUsers() {
//        repository.findAll().forEach(System.out::println);
        return repository.findAll();
    }
}
