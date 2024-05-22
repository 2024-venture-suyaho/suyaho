package com.venture.suyaho.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {

    @EmbeddedId
    private BookId id;

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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumns({
//            @JoinColumn(name = "trade_num", referencedColumnName = "trade_num"),
//            @JoinColumn(name = "user_no", referencedColumnName = "user_no")
//    })
//    private TradeBoard tradeBoard;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "trade_num", nullable = false)
    private TradeBoard tradeBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_no",nullable = false )
    private User user;

    public Book() {
    }

    public Book(BookId id, char bookWriting, char bookCover, char bookDiscoloration, char bookDamage, String bookCompany, TradeBoard tradeBoard, User user) {
        this.id = id;
        this.bookWriting = bookWriting;
        this.bookCover = bookCover;
        this.bookDiscoloration = bookDiscoloration;
        this.bookDamage = bookDamage;
        this.bookCompany = bookCompany;
        this.tradeBoard = tradeBoard;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(tradeBoard, book.tradeBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tradeBoard);
    }

    public BookId getId() {
        return id;
    }

    public void setId(BookId id) {
        this.id = id;
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

    public TradeBoard getTradeBoard() {
        return tradeBoard;
    }

    public void setTradeBoard(TradeBoard tradeBoard) {
        this.tradeBoard = tradeBoard;
    }


}

