package com.venture.suyaho.User.service;

import com.venture.suyaho.User.dto.UserDTO;
import com.venture.suyaho.User.model.User;
import com.venture.suyaho.User.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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

    @Override
    public void register(UserDTO userDTO) throws Exception {
        if (userRepository.existsByUserEmail(userDTO.getEmail())) {
            throw new Exception("Email already in use");
        }
        User user = new User();
        user.setUserName(userDTO.getName());
        user.setUserPhone(userDTO.getPhone());
        user.setUserEmail(userDTO.getEmail());
        user.setUserMajor(userDTO.getMajor());
        user.setUserSchoolNum(userDTO.getSchoolNum());
        // 비밀번호를 암호화하지 않음
        user.setUserPwd(userDTO.getPassword());
        user.setUserRights('N');
        user.setUserPoint(0);
        user.setUserMadeTime(LocalDateTime.now());
        userRepository.save(user);
    }
}
