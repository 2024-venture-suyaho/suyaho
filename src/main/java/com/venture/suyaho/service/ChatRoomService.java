package com.venture.suyaho.service;

import com.venture.suyaho.domain.ChatLog;
import com.venture.suyaho.domain.ChatRoom;
import com.venture.suyaho.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {

    private final ChatRoomRepository repository;

    @Autowired
    public ChatRoomService(ChatRoomRepository repository) {
        this.repository = repository;
    }


    public List<ChatRoom> findAllRoom(){
        return repository.findAllRoom();
    };

    public void insertMes(ChatLog chatLog) {
        System.out.println(chatLog.getMesText());
    }
}
