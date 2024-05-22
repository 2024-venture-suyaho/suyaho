package com.venture.suyaho.controller;


import com.venture.suyaho.dto.UserDTO;
import com.venture.suyaho.model.User;
import com.venture.suyaho.repository.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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

        if (user == null || user.getUserRights() == 'N') {
            model.addAttribute("adminLoginError", user == null ? "Invalid email or password" : "Access denied for non-admin users");
            return "admin-login";
        }

        session.setAttribute("user", user);
        return "admin/adminmain";
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

    @PostMapping("/register")
    public String register(UserDTO userDTO, Model model) {
        logger.info("Received registration request for user: {}", userDTO.getEmail());
        try {
            userService.register(userDTO);
            logger.info("User registered successfully: {}", userDTO.getEmail());
            return "redirect:/login";
        } catch (Exception e) {
            logger.error("Error during registration: ", e);
            model.addAttribute("registerError", e.getMessage());
            return "register";
        }
    }
}
