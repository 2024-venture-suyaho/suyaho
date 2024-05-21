package com.venture.suyaho.User.service;

import com.venture.suyaho.User.dto.UserDTO;
import com.venture.suyaho.User.model.User;

public interface UserService {
    User login(UserDTO userDTO);
}
