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

    @PostMapping("/users/changeMajor")
    public ResponseEntity<?> changeMajor(@RequestBody ChangeMajorRequest request) {
        User user = userRepository.findById(request.getUserNo()).orElse(null);

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if (!user.getUserPwd().equals(request.getCurrentPassword())) {
            return new ResponseEntity<>("Current password is incorrect", HttpStatus.BAD_REQUEST);
        }

        user.setUserMajor(request.getNewMajor());
        userRepository.save(user);

        return new ResponseEntity<>("Major changed successfully", HttpStatus.OK);
    }

    @PostMapping("/users/changePhone")
    public ResponseEntity<?> changePhone(@RequestBody ChangePhoneRequest request) {
        User user = userRepository.findById(request.getUserNo()).orElse(null);

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        if (!user.getUserPwd().equals(request.getCurrentPassword())) {
            return new ResponseEntity<>("Current password is incorrect", HttpStatus.BAD_REQUEST);
        }

        user.setUserPhone(request.getNewPhoneNumber());
        userRepository.save(user);

        return new ResponseEntity<>("Phone number changed successfully", HttpStatus.OK);
    }

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

class ChangeMajorRequest {
    private Long userNo;
    private String currentPassword;
    private String newMajor;

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

    public String getNewMajor() {
        return newMajor;
    }

    public void setNewMajor(String newMajor) {
        this.newMajor = newMajor;
    }
}

class ChangePhoneRequest {
    private Long userNo;
    private String currentPassword;
    private String newPhoneNumber;

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

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
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
