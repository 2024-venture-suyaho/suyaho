package com.venture.suyaho.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "others")
public class Other {

    @Id
    @Column(name = "trade_num")
    private Long id;

    @Column(name = "other_damage")
    private String damage;

    @Column(name = "other_condition")
    private String condition;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}