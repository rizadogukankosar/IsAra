package com.webproject.isara.Model;

public class User {
    private String userID;
    private String name;
    private String surname;
    private String phone;
    private String email;

    public User(String name, String surname, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public User(String userID, String name, String surname, String phone, String email) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public User(){
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
