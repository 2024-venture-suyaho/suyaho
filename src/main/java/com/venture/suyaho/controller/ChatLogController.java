package com.venture.suyaho.controller;

import com.venture.suyaho.domain.ChatLog;
import com.venture.suyaho.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class ChatLogController {

    private final ChatLogService chatLogService;

    @Autowired
    public ChatLogController(ChatLogService chatLogService) {
        this.chatLogService = chatLogService;
    }

    @GetMapping("/allchatlog")
    public List<ChatLog> logList(){
        return chatLogService.findAllLog();
    }
}
