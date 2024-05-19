package com.venture.suyaho.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }

    @GetMapping("/sample/chatroom")
    public String chatroom(Model model) {
        return "sample/chatroom";
    }
}
