package com.venture.suyaho.controller;

import com.venture.suyaho.user.User;
import com.venture.suyaho.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;


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
}
