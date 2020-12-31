package com.example.theTraveler.data;

import java.io.Serializable;

public class Attraction implements Serializable {

    // json
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String BANNER = "banner";
    public static final String IMAGE = "image";
    public static final String DETAILS = "details";
    public static final String COORDINATES = "coordinates";

    private int id;
    private String name;
    private String banner;
    private String image;
    private String details;
    private Coordinates coordinates;


    public Attraction(int id, String name, String banner, String image, String details, Coordinates coordinates) {
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

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
