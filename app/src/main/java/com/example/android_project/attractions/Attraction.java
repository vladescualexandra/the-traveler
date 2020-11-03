package com.example.android_project.attractions;

import java.io.Serializable;

public class Attraction implements Serializable {

    private static int counter = 0;
    private int id;
    private String name;
    private String banner;
    private String image;
    private String details;
    private String coordinates;

    public Attraction() {
        this.id = counter++;
    }


    public Attraction(String name, String banner, String image, String details) {
        this.name = name;
        this.banner = banner;
        this.image = image;
        this.details = details;
        this.id = counter++;
    }

    public Attraction(String name, String banner, String image, String details, String coordinates) {
        this.name = name;
        this.banner = banner;
        this.image = image;
        this.details = details;
        this.coordinates = coordinates;
        this.id = counter++;
    }

    public static int getCounter() {
        return counter;
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
