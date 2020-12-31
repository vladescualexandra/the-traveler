package com.example.theTraveler.databases.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "visits")
public class Visit implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "user")
    private String user;

    @ColumnInfo(name = "attraction")
    private int attraction;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "rating")
    private int rating;



    public Visit(long id, String user, int attraction, String date, int rating) {
        this.id = id;
        this.user = user;
        this.attraction = attraction;
        this.date = date;
        this.rating = rating;
    }

    @Ignore
    public Visit() {

    }

    @Ignore
    public Visit(String user, int attraction, String date, int rating) {
        this.user = user;
        this.attraction = attraction;
        this.date = date;
        this.rating = rating;
    }

    public int getAttraction() {
        return attraction;
    }

    public void setAttraction(int attraction) {
        this.attraction = attraction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", attraction=" + attraction +
                ", date='" + date + '\'' +
                ", rating=" + rating +
                '}';
    }
}
