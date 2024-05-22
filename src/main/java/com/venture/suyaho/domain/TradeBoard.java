package com.venture.suyaho.domain;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;

@Entity
@Table(name = "trade_board")
public class TradeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_num", nullable = false, updatable = false)
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
    @Column(name = "trade_photo")
    private byte[] tradePhoto;

    @Column(name = "trade_time")
    private LocalDateTime tradeTime;

    @Column(name = "trade_complete", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'N'")
    private char tradeComplete = 'N';

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private User user;

    @Lob
    @Column(name = "trade_image")
    private byte[] imageData; // 이미지 데이터 필드


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // 이미지 파일을 처리하기 위한 MultipartFile 필드
    @Transient
    private MultipartFile imageFile;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
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

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
