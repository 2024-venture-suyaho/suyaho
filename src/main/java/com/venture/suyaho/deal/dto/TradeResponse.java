package com.venture.suyaho.deal.dto;

import com.venture.suyaho.deal.entity.Trade;
import java.time.LocalDateTime;

public class TradeResponse {
    private Long tradeNum;
    private String title;
    private String category;
    private LocalDateTime tradeTime;
    private String username;
    private boolean tradeComplete;

    public TradeResponse(Trade trade) {
        this.tradeNum = trade.getId();
        this.title = trade.getTitle();
        this.category = trade.getCategory().getName();
        this.tradeTime = trade.getTradeTime();
        this.username = trade.getUser().getUsername();
        this.tradeComplete = trade.isTradeComplete();
    }

    public Long getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(Long tradeNum) {
        this.tradeNum = tradeNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    // Getters and Setters
}
