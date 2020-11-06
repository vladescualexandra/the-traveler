package com.example.android_project.users;

import com.example.android_project.data.Attraction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAccount implements Serializable {

    private static int counter = 0;
    private final int id;
    private final String username;
    private final String email;
    private final String password;
    private List<Attraction> favorites;
    private List<Attraction> visited;


    public UserAccount(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.favorites = new ArrayList<Attraction>();
        this.visited = new ArrayList<Attraction>();
    }

    public UserAccount(int id, String username, String email, String password, List<Attraction> favorites, List<Attraction> visited) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.favorites = favorites;
        this.visited = visited;
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

        UserAccount userFromDB = new UserAccount(1, "admin", "default e-mail", "admin");

        return userFromDB;
    }

    public static UserAccount getUserAccountByID(int userID) {

        UserAccount userFromDB = new UserAccount(1, "admin", "default e-mail", "admin");

        return userFromDB;
    }

}
