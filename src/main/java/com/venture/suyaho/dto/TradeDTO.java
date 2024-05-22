package com.venture.suyaho.dto;

import com.venture.suyaho.domain.Trade;

import java.time.LocalDateTime;
public class TradeDTO {
    private Long id;
    private String title;
    private String categoryName; // 카테고리 이름 필드
    private LocalDateTime tradeTime;
    private String username;
    private boolean tradeComplete;

    public TradeDTO(Trade trade) {
        this.id = trade.getId();
        this.title = trade.getTitle();
        this.categoryName = trade.getCategory().getName(); // 수정된 코드
        this.tradeTime = trade.getTradeTime();
        this.username = trade.getUser().getUserName();
        this.tradeComplete = trade.isTradeComplete();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public LocalDateTime getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(LocalDateTime tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isTradeComplete() {
        return tradeComplete;
    }

    public void setTradeComplete(boolean tradeComplete) {
        this.tradeComplete = tradeComplete;
    }
}