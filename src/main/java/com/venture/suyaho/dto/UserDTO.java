package com.venture.suyaho.dto;

public class UserDTO {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String major;
    private Integer schoolNum;

    public UserDTO() {
    }

    public UserDTO(String email, String password, String name, String phone, String major, Integer schoolNum) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.major = major;
        this.schoolNum = schoolNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getSchoolNum() {
        return schoolNum;
    }

    public void setSchoolNum(Integer schoolNum) {
        this.schoolNum = schoolNum;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", major='" + major + '\'' +
                ", studentId=" + schoolNum +
                '}';
    }
}
