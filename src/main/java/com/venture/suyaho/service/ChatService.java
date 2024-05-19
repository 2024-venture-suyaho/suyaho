package com.venture.suyaho.service;

import com.venture.suyaho.repository.ChatLogRepository;
import com.venture.suyaho.repository.ChatRoomRepository;
import com.venture.suyaho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;
    //화면 -> WebSocket -> Controller -> Service -> DB에서 잘 기능
    @Autowired
    private ChatLogRepository chatLogRepository;
}
