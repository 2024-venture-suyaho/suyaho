package com.venture.suyaho.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookId implements Serializable {
    @Column(name = "trade_num", nullable = false)
    private Long tradeNum;

    @JoinColumn(name="user_no")
    @Column(name = "user_no", nullable = false)
    private Long userNo;


    public BookId() {}

    public BookId(Long tradeNum, Long userNo) {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookId bookId = (BookId) o;
        return Objects.equals(tradeNum, bookId.tradeNum) && Objects.equals(userNo, bookId.userNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradeNum, userNo);
    }

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
}
