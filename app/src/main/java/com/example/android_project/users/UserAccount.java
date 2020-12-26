package com.example.android_project.users;

import com.example.android_project.data.Attraction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserAccount implements Serializable {

    private String id;
    private String username;
    private String email;
    private String password;

    public UserAccount() {

    }

    public UserAccount(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    public static UserAccount getUserAccountByUsername(String username) {

        UserAccount userFromDB = new UserAccount("1", "admin", "default e-mail", "pass");

        return userFromDB;
    }

    public static UserAccount getUserAccountByID(String userID) {

        UserAccount userFromDB = new UserAccount("1", "admin", "default e-mail", "pass");
        return userFromDB;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "UserAccount{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
