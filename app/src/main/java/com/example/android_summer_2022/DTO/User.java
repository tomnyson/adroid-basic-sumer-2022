package com.example.android_summer_2022.DTO;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private String name;
    private String email;
    private String gioitinh;
    private Date birthday;

    public User(String username, String password, String name, String email, String gioitinh, Date birthday) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.gioitinh = gioitinh;
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
