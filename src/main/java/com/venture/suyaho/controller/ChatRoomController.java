package com.venture.suyaho.controller;

import com.venture.suyaho.domain.ChatLog;
import com.venture.suyaho.domain.ChatRoom;
import com.venture.suyaho.dto.ChatRoomDTO;
import com.venture.suyaho.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    public List<ChatRoom> chatList(){
        return chatRoomService.findAllRoom();
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatRoomDTO sendMessage(ChatRoomDTO chatRoomDTO) throws IllegalAccessException {
        ChatRoomDTO chatInfo = new ChatRoomDTO();
        chatInfo.setChatNum(null);
        chatInfo.setMesTime(LocalDateTime.now());
        chatInfo.setMesText(chatRoomDTO.getMesText());
        chatInfo.setUserName(chatRoomDTO.getUserName());
        System.out.println(chatRoomDTO.getUserName());
        System.out.println(chatRoomDTO.getMesText());
        chatRoomService.saveMes(chatInfo);
        return chatRoomDTO;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatLog addUser(ChatLog chatLog) {
        chatLog.setMesText("New user joined: " + chatLog.getId());
        return chatLog;
    }

}
