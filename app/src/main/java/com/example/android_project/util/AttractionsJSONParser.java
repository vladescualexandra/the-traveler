package com.example.android_project.util;

import android.util.Log;
import android.widget.Toast;

import com.example.android_project.data.Attraction;
import com.example.android_project.data.Coordinates;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AttractionsJSONParser {

    public static final String SOURCE = "source";
    public static final String TITLE = "title";
    public static final String ATTRACTIONS = "attractions";


    public static List<Attraction> retrieveData(String json) {
        if (json != null || !json.isEmpty()) {
            JSONObject mainObject = null;
            try {
                mainObject = new JSONObject(json);
                String source = mainObject.getString(SOURCE);
                String title = mainObject.getString(TITLE);
                JSONArray attractions = mainObject.getJSONArray(ATTRACTIONS);
                return getAttractions(attractions);
            } catch (JSONException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }

    private static List<Attraction> getAttractions(JSONArray array) {
        List<Attraction> results = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                Attraction attraction = getAttractionObject(jsonObject);
                results.add(attraction);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }

    private static Attraction getAttractionObject(JSONObject object) {
        try {
            int id = object.getInt(Attraction.ID);
            String name = object.getString(Attraction.NAME);
            String banner = object.getString(Attraction.BANNER);
            String image = object.getString(Attraction.IMAGE);
            String details = object.getString(Attraction.DETAILS);
            JSONObject jsonObject = object.getJSONObject(Attraction.COORDINATES);
            Coordinates coordinates = getCoordinates(jsonObject);
            return new Attraction(id, name, banner, image, details, coordinates);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Coordinates getCoordinates(JSONObject jsonObject) {
        try {
            double lat = jsonObject.getDouble(Coordinates.LATITUDE);
            double lng = jsonObject.getDouble(Coordinates.LONGITUDE);
            String marker = jsonObject.getString(Coordinates.MARKER);
            return new Coordinates(lat, lng, marker);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
