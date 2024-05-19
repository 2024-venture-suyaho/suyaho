package com.venture.suyaho.repository;

import com.venture.suyaho.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
//    void insertChatRoom(ChatRoom chatRoom);
}
