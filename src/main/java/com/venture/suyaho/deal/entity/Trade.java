package com.venture.suyaho.deal.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "trade_board")
public class Trade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_num")
    private Long id;

    @Column(name = "trade_time")
    private LocalDateTime tradeTime;

    @Column(name = "trade_title")
    private String title;

    @Column(name = "trade_product")
    private String product;

    @Column(name = "trade_quantity")
    private int quantity;

    @Column(name = "trade_price")
    private int price;

    @Column(name = "trade_detail")
    private String detail;

    @Column(name = "trade_condition")
    private String condition;

    @Column(name = "trade_photo")
    private String photo;

    @Column(name = "trade_complete")
    private boolean tradeComplete;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isTradeComplete() {
        return tradeComplete;
    }

    public void setTradeComplete(boolean tradeComplete) {
        this.tradeComplete = tradeComplete;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(LocalDateTime tradeTime) {
        this.tradeTime = tradeTime;
    }

}