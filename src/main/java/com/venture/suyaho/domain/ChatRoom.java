package com.venture.suyaho.domain;

import jakarta.persistence.*;

@Entity
@Table(name="chat_room")
public class ChatRoom {

    @Id
    @Column(name="chat_num")
    private long chatNum;


    @Column(name="user_no")
    private long userNo;
}
