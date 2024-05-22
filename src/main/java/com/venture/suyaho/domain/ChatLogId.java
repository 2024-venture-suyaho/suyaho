package com.venture.suyaho.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class ChatLogId implements Serializable {

    @Column(name = "mes_num", nullable = false)
    private Long mesNum;

    @ManyToOne
    @JoinColumn(name = "chat_num", nullable = false)
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private User userNo;

    public ChatLogId() {}

    public ChatLogId(Long mesNum, ChatRoom chatRoom, User userNo) {
        this.mesNum = mesNum;
        this.chatRoom = chatRoom;
        this.userNo = userNo;
    }

    public long getMesNum() {
        return mesNum;
    }

    public void setMesNum() {
        this.mesNum = mesNum;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public User getUserNo() {
        return userNo;
    }

    public void setUserNo(User userNo) {
        this.userNo = userNo;
    }
}
