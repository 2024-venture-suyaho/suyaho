package com.venture.suyaho.controller;

import com.venture.suyaho.model.User;
import com.venture.suyaho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserPageController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        User user = userRepository.findById(request.getUserNo()).orElse(null);

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if (!user.getUserPwd().equals(request.getCurrentPassword())) {
            return new ResponseEntity<>("Current password is incorrect", HttpStatus.BAD_REQUEST);
        }

        user.setUserPwd(request.getNewPassword());
        userRepository.save(user);

        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }
}

class ChangePasswordRequest {
    private Long userNo;
    private String currentPassword;
    private String newPassword;

    // Getters and Setters

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
