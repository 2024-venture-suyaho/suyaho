package com.venture.suyaho.controller;

import com.venture.suyaho.dto.UserDTO;
import com.venture.suyaho.model.User;
import com.venture.suyaho.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    /* ㅎㅏ... 구현 */
    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession session, Model model) {
        User user = userService.login(userDTO);

        if (user == null) {
            model.addAttribute("loginError", true);
            return "login";
        }

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
