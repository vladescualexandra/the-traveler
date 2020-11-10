package com.example.android_project.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AttractionsJSONParser {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String BANNER = "banner";
    private static final String IMAGE = "image";
    private static final String DETAILS = "details";
    private static final String COORDINATES = "coordinates";

    public static List<Attraction> retrieveData(String json) {
        if (json == null || json.isEmpty()) {
            return new ArrayList<>();
        } else {
            return getAttractionsFromJSON(json);
        }
    }

    private static List<Attraction> getAttractionsFromJSON(String json) {
        List<Attraction> results = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i=0; i<array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                Attraction attraction = getAttractionObject(object);
                results.add(attraction);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }

    private static Attraction getAttractionObject(JSONObject object) {
        try {
            int id = object.getInt(ID);
            String name = object.getString(NAME);
            String banner = object.getString(BANNER);
            String image = object.getString(IMAGE);
            String details = object.getString(DETAILS);
            String coordinates = object.getString(COORDINATES);
            return new Attraction(id, name, banner, image, details, coordinates);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
