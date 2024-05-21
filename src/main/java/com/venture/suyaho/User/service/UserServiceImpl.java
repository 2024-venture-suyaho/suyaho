package com.venture.suyaho.User.service;

import com.venture.suyaho.User.dto.UserDTO;
import com.venture.suyaho.User.model.User;
import com.venture.suyaho.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByUserEmailAndUserPwd(userDTO.getEmail(), userDTO.getPassword());
        if (!userOptional.isPresent()) {
            return null;
        }

        return userOptional.get();
    }
}