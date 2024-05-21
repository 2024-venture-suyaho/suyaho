package com.venture.suyaho.domain;

import jakarta.persistence.*;

@Entity
@Table(name="chat_room")
public class ChatRoom {

    @Id
    @Column(name="chat_num")
    private long chatNum;


    @ManyToOne
    @JoinColumn(name="user_no", nullable=false)
    private User user;

    public long getChatNum() {
        return chatNum;
    }

    public void setChatNum(long chatNum) {
        this.chatNum = chatNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
