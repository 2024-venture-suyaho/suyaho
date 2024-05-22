package com.venture.suyaho.repository;


import com.venture.suyaho.dto.UserDTO;
import com.venture.suyaho.model.User;

public interface UserService {
    User login(UserDTO userDTO);
    void register(UserDTO userDTO) throws Exception;
}
