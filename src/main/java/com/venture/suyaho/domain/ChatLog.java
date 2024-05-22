package com.venture.suyaho.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="chat_log")
public class ChatLog {

    @EmbeddedId
    private ChatLogId id;

    @Column(name = "mes_text", length = 255)
    private String mesText;

    @Column(name = "mes_time")
    private LocalDateTime mesTime;

    public ChatLog() {}

    public ChatLog(ChatLogId id, String mesText, LocalDateTime mesTime) {
        this.id = id;
        this.mesText = mesText;
        this.mesTime = mesTime;
    }



    public ChatLogId getId() {
        return id;
    }

    public void setId(ChatLogId id) {
        this.id = id;
    }

    public String getMesText() {
        return mesText;
    }

    public void setMesText(String mesText) {
        this.mesText = mesText;
    }

    public LocalDateTime getMesTime() {
        return mesTime;
    }

    public void setMesTime(LocalDateTime mesTime) {
        this.mesTime = mesTime;
    }
}
