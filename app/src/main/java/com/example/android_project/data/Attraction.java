package com.example.android_project.data;

import java.io.Serializable;

public class Attraction implements Serializable {

    private int id;
    private String name;
    private String banner;
    private String image;
    private String details;
    private String coordinates;


    public Attraction(int id, String name, String banner, String image, String details, String coordinates) {
        this.id = id;
        this.name = name;
        this.banner = banner;
        this.image = image;
        this.details = details;
        this.coordinates = coordinates;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBanner() {
        return banner;
    }

    public String getImage() {
        return image;
    }


    public String getDetails() {
        return details;
    }

    public String getCoordinates() {
        return coordinates;
    }
}
