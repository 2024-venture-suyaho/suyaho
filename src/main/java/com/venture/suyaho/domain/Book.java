package com.venture.suyaho.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_num")
    private Long tradeNum;

    @Column(name = "user_no", nullable = false)
    private Long userNo;

    @Column(name = "book_writing")
    private char bookWriting;

    @Column(name = "book_cover")
    private char bookCover;

    @Column(name = "book_discoloration")
    private char bookDiscoloration;

    @Column(name = "book_damage")
    private char bookDamage;

    @Column(name = "book_company")
    private String bookCompany;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_no", nullable = false, insertable = false, updatable = false)
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trade_num", nullable = false, insertable = false, updatable = false)
    private AdminBoard tradeBoard;

    public Long getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(Long tradeNum) {
        this.tradeNum = tradeNum;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public char getBookWriting() {
        return bookWriting;
    }

    public void setBookWriting(char bookWriting) {
        this.bookWriting = bookWriting;
    }

    public char getBookCover() {
        return bookCover;
    }

    public void setBookCover(char bookCover) {
        this.bookCover = bookCover;
    }

    public char getBookDiscoloration() {
        return bookDiscoloration;
    }

    public void setBookDiscoloration(char bookDiscoloration) {
        this.bookDiscoloration = bookDiscoloration;
    }

    public char getBookDamage() {
        return bookDamage;
    }

    public void setBookDamage(char bookDamage) {
        this.bookDamage = bookDamage;
    }

    public String getBookCompany() {
        return bookCompany;
    }

    public void setBookCompany(String bookCompany) {
        this.bookCompany = bookCompany;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AdminBoard getTradeBoard() {
        return tradeBoard;
    }

    public void setTradeBoard(AdminBoard tradeBoard) {
        this.tradeBoard = tradeBoard;
    }
}