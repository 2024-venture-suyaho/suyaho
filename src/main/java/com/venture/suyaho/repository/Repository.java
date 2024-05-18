package com.venture.suyaho.repository;

import com.venture.suyaho.dtopackage.ChatMessage;
import com.venture.suyaho.dtopackage.ChatRoom;
import com.venture.suyaho.dtopackage.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository {
    public interface UserRepository extends JpaRepository<User, Long> { }

    public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> { }

    public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> { }

}
