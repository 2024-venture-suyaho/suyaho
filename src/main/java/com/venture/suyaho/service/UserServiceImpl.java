package com.venture.suyaho.service;

import com.venture.suyaho.dto.UserDTO;
import com.venture.suyaho.model.User;
import com.venture.suyaho.repository.UserRepository;
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
            // 해당 이메일과 비밀번호를 가진 사용자를 찾지 못한 경우
            return null; // 또는 원하는 처리를 수행할 수 있음
        }
        // 사용자를 찾은 경우
        return userOptional.get();
    }
}
