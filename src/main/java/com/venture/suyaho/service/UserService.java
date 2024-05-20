package com.venture.suyaho.service;

import com.venture.suyaho.model.User;

public interface UserService {
    User login(String user_email, String user_pwd);
}
