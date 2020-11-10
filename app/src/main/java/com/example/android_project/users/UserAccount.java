package com.example.android_project.users;

import com.example.android_project.data.Attraction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAccount implements Serializable {

    private final int id;
    private final String username;
    private final String email;
    private List<Attraction> favorites;
    private List<Attraction> visited;
    private boolean notifications;
    // false - off
    // true - on
    private boolean theme;
    // false - light
    // true - dark


    public UserAccount(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.favorites = new ArrayList<Attraction>();
        this.visited = new ArrayList<Attraction>();
        this.notifications = false;
        this.theme = false;
    }

    public UserAccount(int id, String username, String email, List<Attraction> favorites, List<Attraction> visited) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.favorites = favorites;
        this.visited = visited;
        this.notifications = false;
        this.theme = false;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<Attraction> getFavorites() {
        return favorites;
    }

    public List<Attraction> getVisited() {
        return visited;
    }

    public static UserAccount getUserAccountByUsername(String username) {

        UserAccount userFromDB = new UserAccount(1, "admin", "default e-mail");

        return userFromDB;
    }

    public static UserAccount getUserAccountByID(int userID) {

        UserAccount userFromDB = new UserAccount(1, "admin", "default e-mail");
        return userFromDB;
    }

    public boolean getNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public boolean getTheme() {
        return theme;
    }

    public void setTheme(boolean theme) {
        this.theme = theme;
    }
}
