package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android_project.data.Attraction;
import com.example.android_project.databases.model.Visit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class AddVisitedActivity extends AppCompatActivity {

    Intent intent;
    private Visit visit;
    public static final String VISIT_KEY = "visit_key";

    private Spinner attraction;
    private DatePicker date;
    private RatingBar rating;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visited);

        intent = getIntent();

        if (intent.hasExtra(VISIT_KEY)) {
            visit = (Visit) intent.getSerializableExtra(VISIT_KEY);
//            buildViews(visit);
            Toast.makeText(getApplicationContext(),
                    visit.toString(),
                    Toast.LENGTH_LONG).show();
        }

        initComponents();
    }

    private void buildViews(Visit visit) {
        if (visit == null) {
            return;
        }
//        if (visit.getAttraction() >= 0) {
//            attraction.setSelection(visit.getAttraction());
//        }
//        if (visit.getDate() != null) {
//            String[] pieces = visit.getDate().split("/");
//            // 0 - day
//            // 1 - month
//            // 2 - year
//            date.init(parseInt(pieces[2]), parseInt(pieces[1]), parseInt(pieces[0]), null);
//        }
//        if (visit.getRating() >= 0 && visit.getRating() <= 5) {
//            rating.setRating(visit.getRating());
//        }
        Toast.makeText(getApplicationContext(),
                visit.toString(),
                Toast.LENGTH_LONG).show();
        add.setText(R.string.add_visit_save);
    }

    private void initComponents() {
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
                MainActivity.visitList);
        attraction.setAdapter(adapter);
    }

    private View.OnClickListener addNewVisitEvent() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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