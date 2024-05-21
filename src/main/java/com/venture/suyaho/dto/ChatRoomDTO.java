package com.venture.suyaho.dto;

import java.time.LocalDateTime;


public class ChatRoomDTO {
    private String mesText;

    private String userName;

    private LocalDateTime mesTime;

    private Long chatNum;

    public ChatRoomDTO() {
    }

    public String getMesText() {
        return mesText;
    }

    public void setMesText(String mesText) {
        this.mesText = mesText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getMesTime() {
        return mesTime;
    }

    public void setMesTime(LocalDateTime mesTime) {
        this.mesTime = mesTime;
    }

    public Long getChatNum() {
        return chatNum;
    }

    public void setChatNum(Long chatNum) {
        this.chatNum = chatNum;
    }

    @Override
    public String toString() {
        return "ChatRoomDTO{" +
                "mesText='" + mesText + '\'' +
                ", userName='" + userName + '\'' +
                ", mesTime=" + mesTime +
                ", chatNum=" + chatNum +
                '}';
    }
}
