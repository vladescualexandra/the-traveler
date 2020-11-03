package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.attractions.Attraction;

import org.w3c.dom.Text;

public class AttractionActivity extends AppCompatActivity {

    private static Intent intent;
    private static Attraction attraction;
    private static TextView name;
    private static ImageView image;
    private static TextView details;
    private static TextView maps;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        intent = getIntent();
        attraction = (Attraction) intent.getSerializableExtra("ATTRACTION_KEY");

        initComponents();
        initAttraction();
    }

    private void initComponents() {
        name = findViewById(R.id.attraction_name);
        image = findViewById(R.id.attraction_image);
        details = findViewById(R.id.attraction_details);
        maps = findViewById(R.id.attraction_maps);
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
