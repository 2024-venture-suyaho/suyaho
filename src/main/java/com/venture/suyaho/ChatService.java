package com.venture.suyaho;

import com.venture.suyaho.dtopackage.ChatMessage;
import com.venture.suyaho.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

public class ChatService {
    @Autowired
    private Repository.ChatMessageRepository chatMessageRepository;

    @Autowired
    private Repository.ChatRoomRepository chatRoomRepository;

    @Autowired
    private Repository.UserRepository userRepository;

    public ChatMessage saveMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }
}
