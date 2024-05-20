package com.venture.suyaho.service;

import com.venture.suyaho.dto.UserDTO;
import com.venture.suyaho.model.User;

public interface UserService {
    User login(UserDTO userDTO);
}
