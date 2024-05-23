package com.venture.suyaho.controller;

import com.venture.suyaho.domain.AdminBoard;
import com.venture.suyaho.domain.User;
import com.venture.suyaho.repository.AdminBoardRepository;
import com.venture.suyaho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private AdminBoardRepository adminBoardRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/trades")
    public List<AdminBoard> getTrades() {
        return adminBoardRepository.findAll();
    }

    @GetMapping("/trades/user/{userNo}")
    public List<AdminBoard> getTradesByUserNo(@PathVariable Integer userNo) {
        return adminBoardRepository.findByUser_UserNo(userNo);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/adminpost/search")
    public List<AdminBoard> searchAdminPosts(@RequestParam(required = false) String category, @RequestParam(required = false) String keyword) {
        if (category == null || keyword == null || keyword.isBlank()) {
            return adminBoardRepository.findAll();
        } else {
            switch (category) {
                case "schoolnum":
                    List<User> users = userRepository.findByUserSchoolNumContaining(keyword);
                    if (!users.isEmpty()) {
                        List<Long> userListNum = users.stream().map(User::getUserNo).collect(Collectors.toList());
                        return adminBoardRepository.findByUser_UserNoIn(userListNum);
                    } else {
                        return new ArrayList<>();
                    }
                case "title":
                    return adminBoardRepository.findByTradeTitleContainingIgnoreCase(keyword);
                default:
                    return new ArrayList<>();
            }
        }
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam(required = false) String category, @RequestParam(required = false) String keyword) {
        if (category == null || keyword == null || keyword.isBlank()) {
            return userRepository.findAll();
        }

        List<User> users = new ArrayList<>();
        switch (category) {
            case "name":
                users = userRepository.findByUserNameContainingIgnoreCase(keyword);
                break;
            case "department":
                users = userRepository.findByUserMajorContainingIgnoreCase(keyword);
                break;
            case "studentID":
                try {
                    users.add(userRepository.findByUserSchoolNum(Integer.parseInt(keyword)));
                } catch (NumberFormatException e) {
                }
                break;
        }
        return users;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/userposts/{userNo}")
    public List<AdminBoard> getUserPosts(@PathVariable Integer userNo) {
        return adminBoardRepository.findByUser_UserNo(userNo);
    }

    @PostMapping("/trades/updateStatus/{tradeNum}")
    public ResponseEntity<String> updateTradeStatus(@PathVariable Long tradeNum, @RequestBody Map<String, String> status) {
        AdminBoard trade = adminBoardRepository.findById(tradeNum).orElse(null);
        if (trade == null) {
            return ResponseEntity.notFound().build();
        }
        trade.setTradeComplete(status.get("tradeComplete").charAt(0));
        adminBoardRepository.save(trade);
        return ResponseEntity.ok("Trade status updated successfully");
    }
}