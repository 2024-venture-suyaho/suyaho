package com.venture.suyaho.controller;

import com.venture.suyaho.domain.AdminBoard;
import com.venture.suyaho.domain.User;
import com.venture.suyaho.repository.AdminBoardRepository;
import com.venture.suyaho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api")
public class PostController {

    @Autowired
    private AdminBoardRepository adminBoardRepository;

    @Autowired
    private UserRepository userRepository;

    // 전체 거래 목록을 가져와서 템플릿에 전달
    @GetMapping("/posts")
    @ResponseBody
    public List<AdminBoard> adminPost() {
        return adminBoardRepository.findAll();
    }

    // 특정 사용자의 거래 목록을 가져오기
    @GetMapping("/posts/{userNo}")
    @ResponseBody
    public List<AdminBoard> getUserPosts(@PathVariable Integer userNo) {
        return adminBoardRepository.findByUser_UserNo(userNo);
    }

    // 검색 기능을 제공
    @GetMapping("/posts/search")
    @ResponseBody
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

    // 거래 상태 업데이트
    @PostMapping("/posts/updateStatus/{tradeNum}")
    @ResponseBody
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
