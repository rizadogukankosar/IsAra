package com.webproject.isara.Model;

import java.io.Serializable;

public class Advert implements Serializable {
    private String title;
    private String description;
    private String city;
    private String wage;
    private User user;
    private String userID;
    private String advertID;

    public Advert(String title, String description, String city, String wage, User user) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.wage = wage;
        this.user = user;
    }

    public Advert(String title, String description, String city, String wage, String userID) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.wage = wage;
        this.userID = userID;
    }

    public Advert(){

    }

    public String getAdvertID() {
        return advertID;
    }

    public void setAdvertID(String advertID) {
        this.advertID = advertID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
