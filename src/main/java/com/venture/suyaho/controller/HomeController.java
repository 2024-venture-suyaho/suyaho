package com.venture.suyaho.controller;

import com.venture.suyaho.admin.AdminBoard;
import com.venture.suyaho.admin.AdminBoardRepository;
import com.venture.suyaho.admin.User;
import com.venture.suyaho.admin.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminBoardRepository adminBoardRepository;

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/login")
    public String login(HttpSession session) {
        session.setAttribute("user", "exampleUser");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String mypage(Model model) {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            User user = users.get(1); // 첫 번째 사용자 정보를 가져옵니다.
            model.addAttribute("user", user); // 사용자 정보를 모델에 추가
        }
        return "mypage/admin-userpage";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users); // 사용자 목록을 모델에 추가합니다.
        return "admin/adminpage"; // 관리자 페이지를 위한 HTML 템플릿의 이름을 반환합니다.
    }

    @GetMapping("/adminpost")
    public String adminPost(Model model) {
        // trade_board 테이블에서 데이터를 가져옵니다.
        List<AdminBoard> boardList = adminBoardRepository.findAll();
        // 각 AdminBoard 객체에 schoolNum을 설정합니다.
        boardList.forEach(board -> {
            board.setSchoolNum(board.getUser().getUserSchoolNum());
        });
        model.addAttribute("trade_board", boardList); // 모델에 tradeList를 추가합니다.
        return "admin/adminpost";
    }

    @DeleteMapping("/api/trades/{tradeNum}")
    @ResponseBody
    public ResponseEntity<String> deleteTrade(@PathVariable Long tradeNum) {
        adminBoardRepository.deleteById(tradeNum);
        return ResponseEntity.ok("Trade deleted");
    }

    @Service
    public class UserService {

        @Autowired
        private UserRepository userRepository;

        public List<User> getAllUsers() {
            return userRepository.findAll();
        }
    }

    @RestController
    @RequestMapping("/api")
    class ApiController {

        @Autowired
        private AdminBoardRepository adminBoardRepository;

        @GetMapping("/trades")
        public List<AdminBoard> getTrades() {
            return adminBoardRepository.findAll();
        }
    }

}
