package com.venture.suyaho.service;

import com.venture.suyaho.model.User;

public interface UserService {
    User login(String username, String password);
}
