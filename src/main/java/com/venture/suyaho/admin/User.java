package com.venture.suyaho.admin;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Integer userNo;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_phone", nullable = false)
    private String userPhone;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "user_major", nullable = false)
    private String userMajor;

    @Column(name = "user_school_num", nullable = false)
    private Integer userSchoolNum;

    @Column(name = "user_pwd", nullable = false)
    private String userPwd;

    @Column(name = "user_rights", nullable = false)
    private char userRights;

    @Column(name = "user_point", nullable = false)
    private Integer userPoint;

    @Column(name = "user_made_time", nullable = false)
    private LocalDateTime userMadeTime;

    @Lob
    @Column(name = "user_img")
    private byte[] userImg;

    // Getters and Setters
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
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

    public Integer getUserSchoolNum() {
        return userSchoolNum;
    }

    public void setUserSchoolNum(Integer userSchoolNum) {
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

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
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
}
