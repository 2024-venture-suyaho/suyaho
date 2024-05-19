package com.venture.suyaho.user;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_phone", nullable = false)
    private String userPhone;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_major", nullable = false)
    private String userMajor;

    @Column(name = "user_school_num", nullable = false)
    private int userSchoolNum;

    @Column(name = "user_pwd", nullable = false)
    private String userPwd;

    @Column(name = "user_rights", nullable = false)
    private char userRights;

    @Column(name = "user_point", nullable = false)
    private int userPoint;

    @Column(name = "user_made_time", nullable = false)
    private LocalDateTime userMadeTime;

    @Lob
    @Column(name = "user_img")
    private byte[] userImg;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdminBoard> adminBoards;

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public void setUserMajor(String userMajor) {
        this.userMajor = userMajor;
    }

    public int getUserSchoolNum() {
        return userSchoolNum;
    }

    public void setUserSchoolNum(int userSchoolNum) {
        this.userSchoolNum = userSchoolNum;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public char getUserRights() {
        return userRights;
    }

    public void setUserRights(char userRights) {
        this.userRights = userRights;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    public LocalDateTime getUserMadeTime() {
        return userMadeTime;
    }

    public void setUserMadeTime(LocalDateTime userMadeTime) {
        this.userMadeTime = userMadeTime;
    }

    public byte[] getUserImg() {
        return userImg;
    }

    public void setUserImg(byte[] userImg) {
        this.userImg = userImg;
    }

    public List<AdminBoard> getAdminBoards() {
        return adminBoards;
    }

    public void setAdminBoards(List<AdminBoard> adminBoards) {
        this.adminBoards = adminBoards;
    }
}
