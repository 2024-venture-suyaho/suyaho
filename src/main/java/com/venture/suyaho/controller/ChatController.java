package com.venture.suyaho.controller;

import com.venture.suyaho.dtopackage.ChatMessage;
import com.venture.suyaho.repository.Repository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import java.time.LocalDateTime;

//@Controller
//@RequestMapping("sample")
//public class ChatController {
//
//    //랜덤 아이디 부여 (나중에 ID 속성 부여하는곳)
//    @GetMapping("chatroom")
//    public ModelAndView chatGet(ModelAndView mv){
//        mv.addObject("name", UUID.randomUUID().toString());
//        mv.setViewName("/sample/chatroom");
//        return mv;
//    }
//}

@Controller
@RequestMapping("sample")
public class ChatController {

    private final Repository.ChatMessageRepository chatMessageRepository;
    private final Repository.ChatRoomRepository chatRoomRepository;
    private final Repository.UserRepository userRepository;

    public ChatController(Repository.ChatMessageRepository chatMessageRepository,
                          Repository.ChatRoomRepository chatRoomRepository,
                          Repository.UserRepository userRepository) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        chatMessageRepository.save(message);
        return new ChatMessage(HtmlUtils.htmlEscape(message.getContent()),
                message.getTimestamp());
    }
}