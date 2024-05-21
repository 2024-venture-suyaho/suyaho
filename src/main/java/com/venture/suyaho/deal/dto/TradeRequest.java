package com.venture.suyaho.deal.dto;

import com.google.errorprone.annotations.Var;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class TradeRequest {
    private String title;
    private boolean bookWriting;
    private String bookCover;
    private boolean bookDiscoloration;
    private boolean bookDamage;
    private String productName;
    private int quantity;
    private double price;
    private String description;
    private Long categoryId;
    private MultipartFile image;
    private Var publisher;
    private int userNo; // 추가
    private boolean tradeComplete;
    private String tradeTime;

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }



    public boolean isTradeComplete() {
        return tradeComplete;
    }

    public void setTradeComplete(boolean tradeComplete) {
        this.tradeComplete = tradeComplete;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public MultipartFile  getImage() {
        return image;
    }




    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isBookWriting() {
        return bookWriting;
    }

    public void setBookWriting(boolean bookWriting) {
        this.bookWriting = bookWriting;
    }


    public boolean isBookDiscoloration() {
        return bookDiscoloration;
    }

    public void setBookDiscoloration(boolean bookDiscoloration) {
        this.bookDiscoloration = bookDiscoloration;
    }

    public boolean isBookDamage() {
        return bookDamage;
    }

    public void setBookDamage(boolean bookDamage) {
        this.bookDamage = bookDamage;
    }



    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(Var publisher) {
        this.publisher = publisher;
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

}
