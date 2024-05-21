package com.venture.suyaho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @RequestParam String name,
            @RequestParam String major,
            @RequestParam String phone,
            @RequestParam String email,
            @RequestParam String studentId,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam(required = false) String agree,
            Model model) {

        /* 로직 구현 */

        model.addAttribute("registrationSuccess", true);
        return "register";
    }
}