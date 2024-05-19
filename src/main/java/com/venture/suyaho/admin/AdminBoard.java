package com.venture.suyaho.admin;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "trade_board")
public class AdminBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_num")
    private Long tradeNum;

    @Column(name = "trade_category", nullable = false)
    private String tradeCategory;

    @Column(name = "trade_title", nullable = false)
    private String tradeTitle;

    @Column(name = "trade_product", nullable = false)
    private String tradeProduct;

    @Column(name = "trade_quantity", nullable = false)
    private int tradeQuantity;

    @Column(name = "trade_price", nullable = false)
    private int tradePrice;

    @Column(name = "trade_text", nullable = false)
    private String tradeText;

    @Column(name = "trade_condition", nullable = false)
    private String tradeCondition;

    @Lob
    @Column(name = "trade_photo", nullable = false)
    private byte[] tradePhoto;

    @Column(name = "trade_time", nullable = false)
    private LocalDateTime tradeTime;

    @Column(name = "trade_complete", nullable = false)
    private char tradeComplete;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_no", nullable = false)
    private User user;

    @Transient
    private int schoolNum;

    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSchoolNum() {
        return user != null ? user.getUserSchoolNum() : 0;
    }

    public void setSchoolNum(int schoolNum) {
        this.schoolNum = schoolNum;
    }

    public Long getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(Long tradeNum) {
        this.tradeNum = tradeNum;
    }

    public String getTradeCategory() {
        return tradeCategory;
    }

    public void setTradeCategory(String tradeCategory) {
        this.tradeCategory = tradeCategory;
    }

    public String getTradeTitle() {
        return tradeTitle;
    }

    public void setTradeTitle(String tradeTitle) {
        this.tradeTitle = tradeTitle;
    }

    public String getTradeProduct() {
        return tradeProduct;
    }

    public void setTradeProduct(String tradeProduct) {
        this.tradeProduct = tradeProduct;
    }

    public int getTradeQuantity() {
        return tradeQuantity;
    }

    public void setTradeQuantity(int tradeQuantity) {
        this.tradeQuantity = tradeQuantity;
    }

    public int getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(int tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getTradeText() {
        return tradeText;
    }

    public void setTradeText(String tradeText) {
        this.tradeText = tradeText;
    }

    public String getTradeCondition() {
        return tradeCondition;
    }

    public void setTradeCondition(String tradeCondition) {
        this.tradeCondition = tradeCondition;
    }

    public byte[] getTradePhoto() {
        return tradePhoto;
    }

    public void setTradePhoto(byte[] tradePhoto) {
        this.tradePhoto = tradePhoto;
    }

    public LocalDateTime getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(LocalDateTime tradeTime) {
        this.tradeTime = tradeTime;
    }

    public char getTradeComplete() {
        return tradeComplete;
    }

    public void setTradeComplete(char tradeComplete) {
        this.tradeComplete = tradeComplete;
    }
}