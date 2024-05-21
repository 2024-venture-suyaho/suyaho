package com.venture.suyaho.deal.entity;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

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
    private double price;

    @Column(name = "trade_detail")
    private String detail;

    @Column(name = "trade_condition")
    private String condition;

    @Column(name = "trade_complete")
    private boolean tradeComplete;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @OneToOne(mappedBy = "trade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Book book;



    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(LocalDateTime tradeTime) {
        this.tradeTime = tradeTime;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Lob
    @Column(name = "trade_image")
    private MultipartFile image;

    // Getters and Setters

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }


}
