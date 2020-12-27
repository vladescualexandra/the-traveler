package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.android_project.data.Attraction;
import com.example.android_project.data.Visit;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddVisitedActivity extends AppCompatActivity {

    Intent intent;
    public static List<String> attrs = new ArrayList<>();
    public static final String VISIT_KEY = "visit_key";

    private Spinner attraction;
    private DatePicker date;
    private RatingBar rating;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visited);

        initComponents();
    }

    private void initComponents() {
        intent = getIntent();
        for (Attraction a : MainActivity.attractionList) {
            attrs.add(a.getName());
        }
        attraction = findViewById(R.id.add_visited_spinner_attractions);
        addAttractionAdapter();
        date = findViewById(R.id.add_visited_date);
        rating = findViewById(R.id.add_visited_rating);
        add = findViewById(R.id.add_visited_btn_add);

        add.setOnClickListener(addNewVisitEvent());


    }

    private void addAttractionAdapter() {
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                attrs);
        attraction.setAdapter(adapter);
    }

    private View.OnClickListener addNewVisitEvent() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = date.getDayOfMonth() + "/" + date.getMonth() + "/" + date.getYear();

                Visit visit = new Visit((int) attraction.getSelectedItemId(),
                        dateString,
                        (int) rating.getRating());


                intent.putExtra(VISIT_KEY, visit);
                setResult(RESULT_OK, intent);
                finish();

            }
        };
    }
}