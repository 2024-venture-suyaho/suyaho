package com.venture.suyaho.deal.entity;

import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "trade_num")
    private Long id;

    @Column(name = "book_writing")
    private String writing;

    @Column(name = "book_cover")
    private String cover;

    @Column(name = "book_discoloration")
    private String discoloration;

    @Column(name = "book_damage")
    private String damage;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private com.venture.suyaho.deal.entity.User user;

    @Column(name = "book_company")
    private String company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDiscoloration() {
        return discoloration;
    }

    public void setDiscoloration(String discoloration) {
        this.discoloration = discoloration;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public User getUser() {
        return (User) user;
    }

    public void setUser(User user) {
        this.user = (com.venture.suyaho.deal.entity.User) user;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}