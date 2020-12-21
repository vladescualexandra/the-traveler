package com.example.android_project.data;

import java.io.Serializable;

public class Coordinates implements Serializable {

    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lng";
    public static final String MARKER = "marker";

    private double lat;
    private double lng;
    private String marker;

    public Coordinates(double lat, double lng, String marker) {
        this.lat = lat;
        this.lng = lng;
        this.marker = marker;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
}
