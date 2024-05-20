package com.venture.suyaho.controller;

import com.venture.suyaho.model.User;
import com.venture.suyaho.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    /* ㅎㅏ... 구현 */
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        // DB에서 조회
        User user = userRepository.findByUserEmail(email);

        // 없으면
        if (user == null || !user.getUserPwd().equals(password)) {
            model.addAttribute("loginError", true);
            return "login";
        }

        // 성공 -> 세션에 저장
        session.setAttribute("user", user);
        return "redirect:/";
    }




    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/admin-login")
    public String adminLoginForm() {
        return "admin-login";
    }
}
