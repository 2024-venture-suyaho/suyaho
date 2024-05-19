package com.venture.suyaho.repository;

import com.venture.suyaho.domain.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {

}
