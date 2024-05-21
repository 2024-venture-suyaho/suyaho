package com.venture.suyaho.service;

import com.venture.suyaho.domain.ChatLog;
import com.venture.suyaho.repository.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatLogService {

    private final ChatLogRepository repository;

    @Autowired
    public ChatLogService(ChatLogRepository repository) {
        this.repository = repository;
    }

    public List<ChatLog> findAllLog() {
        return repository.findAllLog();
    }
}
