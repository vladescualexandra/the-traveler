package com.example.android_project.databases.model;

import androidx.annotation.NonNull;
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

    @ColumnInfo(name = "attraction")
    private int attraction;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "rating")
    private int rating;


    @Ignore
    public Visit(int attraction, String date, int rating) {
        this.attraction = attraction;
        this.date = date;
        this.rating = rating;
    }

    public Visit(long id, int attraction, String date, int rating) {
        this.id = id;
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

    @NonNull
    @Override
    public String toString() {
        return this.id + ", " + this.attraction + ", " + this.date + ", " + this.rating;
    }
}
