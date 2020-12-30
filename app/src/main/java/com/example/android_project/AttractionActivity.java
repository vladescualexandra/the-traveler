package com.example.android_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.data.Attraction;
import com.example.android_project.data.Coordinates;
import com.example.android_project.databases.model.UserAccount;

public class AttractionActivity extends AppCompatActivity {

    public static final String COORDINATES = "coordinates";


    private static Attraction attraction;
    private static TextView name;
    private static ImageView image;
    private static TextView details;
    private static TextView maps;

    private static final String ATTRACTION_KEY = "attraction_key";

    private static Intent intent;
    private static UserAccount user;
    private static final String USER_KEY = "user_key";
    private static SharedPreferences userInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        intent = getIntent();
        attraction = (Attraction) intent.getSerializableExtra(ATTRACTION_KEY);
        user = (UserAccount) intent.getSerializableExtra(USER_KEY);

        initComponents();
        initAttraction();
    }

    private void initComponents() {
        name = findViewById(R.id.attraction_name);
        image = findViewById(R.id.attraction_image);
        details = findViewById(R.id.attraction_details);
        maps = findViewById(R.id.attraction_maps);
        maps.setOnClickListener(openMapsEventListener());
    }

    private View.OnClickListener openMapsEventListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                SharedPreferences coordinates = getSharedPreferences(COORDINATES, MODE_PRIVATE);
                SharedPreferences.Editor editor = coordinates.edit();

                editor.putFloat(Coordinates.LATITUDE, (float) attraction.getCoordinates().getLat());
                editor.putFloat(Coordinates.LONGITUDE, (float) attraction.getCoordinates().getLng());
                editor.putString(Coordinates.MARKER, attraction.getCoordinates().getMarker());
                editor.apply();

                startActivity(intent);
            }
        };
    }

    private void initAttraction() {
        setText(name, attraction.getName());
        setImage(image, attraction.getImage());
        setText(details, attraction.getDetails());
    }

    private void setImage(ImageView iv, String image) {
        if (image != null && !image.isEmpty()) {

            int resID = getResources()
                    .getIdentifier(image, "drawable", getPackageName());
            iv.setImageResource(resID);
        } else {
            int resID = getResources()
                    .getIdentifier("image_unavailable", "drawable", getPackageName());
            iv.setImageResource(resID);
        }
    }


    private void setText(TextView tv, String value) {
        if (value != null && !value.isEmpty()) {
            tv.setText(value);
        } else {
            tv.setText(getString(R.string.default_text));
        }
    }






}
