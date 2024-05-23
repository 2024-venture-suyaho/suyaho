package com.venture.suyaho.service;


import com.venture.suyaho.domain.User;
import com.venture.suyaho.dto.UserDTO;


public interface UserService {
    User login(UserDTO userDTO);
    void register(UserDTO userDTO) throws Exception;
}
