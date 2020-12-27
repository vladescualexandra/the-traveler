package com.example.android_project.data;

import java.io.Serializable;

public class Visit implements Serializable {

    private int id;
    private String date;
    private int rating;

    public Visit() {
    }

    public Visit(int id, String date, int rating) {
        this.id = id;
        this.date = date;
        this.rating = rating;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
