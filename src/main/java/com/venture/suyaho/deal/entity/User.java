package com.venture.suyaho.deal.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long id;

    // 사용자 정보 필드 추가

    @OneToMany
    private List<Trade> tradeList;

    @OneToMany
    private List<Book> bookList;

    @OneToMany
    private List<Other> othersList;


    public Long getId() {
        return id;
    }
    @Column(name = "username")
    private String username;
 /*   @OneToMany(mappedBy = "users")
    private List<Book> books;
*/


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Trade> getTradeList() {
        return tradeList;
    }

    public void setTradeList(List<Trade> tradeList) {
        this.tradeList = tradeList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Other> getOthersList() {
        return othersList;
    }

    public void setOthersList(List<Other> othersList) {
        this.othersList = othersList;
    }


}
