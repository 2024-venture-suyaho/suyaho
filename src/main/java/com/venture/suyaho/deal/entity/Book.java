package com.venture.suyaho.deal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
    private Trade trade;

    @Column(name = "book_writing")
    private char writing;

    @Column(name = "book_cover")
    private char cover;

    @Column(name = "book_discoloration")
    private char discoloration;

    @Column(name = "book_damage")
    private char damage;

    @Column(name = "book_company")
    private String company;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public char getWriting() {
        return writing;
    }

    public void setWriting(char writing) {
        this.writing = writing;
    }

    public char getCover() {
        return cover;
    }

    public void setCover(char cover) {
        this.cover = cover;
    }

    public char getDiscoloration() {
        return discoloration;
    }

    public void setDiscoloration(char discoloration) {
        this.discoloration = discoloration;
    }

    public char getDamage() {
        return damage;
    }

    public void setDamage(char damage) {
        this.damage = damage;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}

