package com.venture.suyaho.repository;

import com.venture.suyaho.domain.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {

    @Query("SELECT cl FROM ChatLog cl")
    List<ChatLog> findAllLog();
}
