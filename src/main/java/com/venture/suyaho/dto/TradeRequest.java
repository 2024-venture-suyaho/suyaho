package com.venture.suyaho.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class TradeRequest {
    private String title;
    private Character bookWriting;
    private Character bookCover;
    private Character bookDiscoloration;
    private Character bookDamage;
    private String productName;
    private int quantity;
    private double price;
    private String description;
    private Long categoryId;
    private MultipartFile image;
    private String publisher;
    private int userNo; // 추가
    private String tradeComplete;
    private String tradeTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Character getBookWriting() {
        return bookWriting;
    }

    public void setBookWriting(Character bookWriting) {
        this.bookWriting = bookWriting;
    }

    public Character getBookCover() {
        return bookCover;
    }

    public void setBookCover(Character bookCover) {
        this.bookCover = bookCover;
    }

    public Character getBookDiscoloration() {
        return bookDiscoloration;
    }

    public void setBookDiscoloration(Character bookDiscoloration) {
        this.bookDiscoloration = bookDiscoloration;
    }

    public Character getBookDamage() {
        return bookDamage;
    }

    public void setBookDamage(Character bookDamage) {
        this.bookDamage = bookDamage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getTradeComplete() {
        return tradeComplete;
    }

    public void setTradeComplete(String tradeComplete) {
        this.tradeComplete = tradeComplete;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }
}