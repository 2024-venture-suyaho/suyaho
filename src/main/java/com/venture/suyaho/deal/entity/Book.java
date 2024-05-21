package com.venture.suyaho.deal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY)
    private Trade trade;

    @Column(name = "book_writing")
    private boolean writing;

    @Column(name = "book_cover")
    private String cover;

    @Column(name = "book_discoloration")
    private boolean discoloration;

    @Column(name = "book_damage")
    private boolean damage;

    @Column(name = "book_company")
    private String company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public boolean isWriting() {
        return writing;
    }

    public void setWriting(boolean writing) {
        this.writing = writing;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isDiscoloration() {
        return discoloration;
    }

    public void setDiscoloration(boolean discoloration) {
        this.discoloration = discoloration;
    }

    public boolean isDamage() {
        return damage;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
