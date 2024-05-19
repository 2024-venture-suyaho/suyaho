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

import java.util.ArrayList;
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
        List<User> users = userRepository.findAll(); // userRepository를 사용하여 모든 유저 데이터를 가져옴
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

    @RestController
    @RequestMapping("/api")
    class ApiController {

        @Autowired
        private AdminBoardRepository adminBoardRepository;

        @Autowired
        private UserRepository userRepository;

        @GetMapping("/trades")
        public List<AdminBoard> getTrades() {
            return adminBoardRepository.findAll();
        }

        @GetMapping("/users") // 사용자 목록을 반환하는 API 엔드포인트
        public List<User> getUsers() {
            return userRepository.findAll();
        }

        @GetMapping("/search") // 사용자 검색을 처리하는 API 엔드포인트
        public List<User> searchUsers(@RequestParam(required = false) String category, @RequestParam(required = false) String keyword) {
            if (category == null || keyword == null || keyword.isBlank()) {
                return userRepository.findAll(); // 텍스트를 입력하지 않았을 경우 전체 목록 반환
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
                        // 학번이 숫자가 아닌 경우 처리
                    }
                    break;
            }
            return users;
        }
        @DeleteMapping("/users/{id}")
        public void deleteUser(@PathVariable Long id) {
            // id에 해당하는 사용자를 삭제합니다.
            userRepository.deleteById(id);
        }
    }

    @Service
    public class UserService {

        @Autowired
        private UserRepository userRepository;

        public List<User> getAllUsers() {
            return userRepository.findAll();
        }
    }

}
