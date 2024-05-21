package com.venture.suyaho.User.controller;

import com.venture.suyaho.User.dto.UserDTO;
import com.venture.suyaho.User.model.User;
import com.venture.suyaho.User.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(UserDTO userDTO, HttpSession session, Model model) {
        User user = userService.login(userDTO);

        if (user == null || user.getUserRights() == 'Y') {
            model.addAttribute("loginError", user == null ? "Invalid email or password" : "Login as an administrator");
            return "login";
        }

        session.setAttribute("user", user);
        return "redirect:/";
    }


    @GetMapping("/admin-login")
    public String adminLoginForm() {
        return "admin-login";
    }

    @PostMapping("/admin-login")
    public String adminLogin(UserDTO userDTO, HttpSession session, Model model) {
        User user = userService.login(userDTO);

        if (user == null || !"Y".equals(user.getUserRights())) {
            model.addAttribute("loginError", user == null ? "Invalid email or password" : "Access denied for non-admin users");
            return "admin-login";
        }

        session.setAttribute("user", user);
        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }
}
