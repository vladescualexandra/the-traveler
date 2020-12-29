package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.android_project.databases.model.UserAccount;
import com.example.android_project.databases.model.Visit;
import com.example.android_project.util.ChartView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Map<Integer, Integer> source = getSource(VisitedActivity.visitList);

        for (Map.Entry<Integer, Integer> entry : source.entrySet()) {
            String key = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());

            Log.e(key, value);
        }


        if (source != null) {

            ChartView view = new ChartView(getApplicationContext(), source);
            setContentView(view);
        } else {
            finish();
            Toast.makeText(getApplicationContext(),
                    "Open Visited first, please!", Toast.LENGTH_LONG).show();
        }
    }

    private Map<Integer, Integer> getSource(List<Visit> visits) {
        if (visits == null || visits.isEmpty()) {
            return null;
        } else {
            Map<Integer, Integer> source = new HashMap<>();

            for (Visit visit : visits) {
                if (source.containsKey(visit.getRating())) {
                    Integer currentValue = source.get(visit.getRating());
                    Integer newValue = (currentValue != null ? currentValue : 0) + 1;
                    source.put(visit.getRating(), newValue);
                } else {
                    source.put(visit.getRating(), 1);
                }
            }
            return source;
        }
    }

}