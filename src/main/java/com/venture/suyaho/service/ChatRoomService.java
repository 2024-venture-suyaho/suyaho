package com.venture.suyaho.service;

import com.venture.suyaho.domain.ChatLog;
import com.venture.suyaho.domain.ChatLogId;
import com.venture.suyaho.domain.ChatRoom;
import com.venture.suyaho.domain.User;
import com.venture.suyaho.dto.ChatRoomDTO;
import com.venture.suyaho.repository.ChatLogRepository;
import com.venture.suyaho.repository.ChatRoomRepository;
import com.venture.suyaho.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {

    private final ChatRoomRepository repository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatLogRepository chatLogRepository;

    @Autowired
    public ChatRoomService(ChatRoomRepository repository, UserRepository userRepository, ChatRoomRepository chatRoomRepository, ChatLogRepository chatLogRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.chatLogRepository = chatLogRepository;
    }


    public List<ChatRoom> findAllRoom(){
        return repository.findAllRoom();
    };

    public void insertMes(ChatRoomDTO chatRoomDTO) {

    }

    public void insertUserName(ChatRoom chatRoom) {
        System.out.println(chatRoom.getUser());
    }

    @Transactional
    public void saveMes(ChatRoomDTO chatInfo) throws IllegalAccessException {
        User user = userRepository.findByUserName(chatInfo.getUserName());
        if(user == null) {
            throw new IllegalAccessException("User not Found: " + chatInfo.getUserName());
        }

        ChatRoom chatRoom= new ChatRoom();
        chatRoom.setUser(user);
        chatRoomRepository.save(chatRoom);

        ChatLogId chatLogId = new ChatLogId();

        chatLogId.setChatRoom(chatRoom);
        chatLogId.setUserNo(user);

        ChatLog chatLog = new ChatLog();
        chatLog.setId(chatLogId);
        chatLog.setMesText(chatInfo.getMesText());
        chatLog.setMesTime(chatInfo.getMesTime());
        chatLogRepository.save(chatLog);
    }
}
