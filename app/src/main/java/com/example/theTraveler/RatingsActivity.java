package com.example.theTraveler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.theTraveler.async.Callback;
import com.example.theTraveler.databases.model.Visit;
import com.example.theTraveler.databases.service.VisitService;
import com.example.theTraveler.util.RatingsChart;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingsActivity extends AppCompatActivity {

    List<Visit> visits = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VisitService visitService = new VisitService(getApplicationContext());
        String user = getSharedPreferences(StartActivity.USER_KEY, MODE_PRIVATE)
                .getString(StartActivity.ID, null);
        visitService.getAll(user, getRatingsCallback());
    }

    private Callback<List<Visit>> getRatingsCallback() {
        return new Callback<List<Visit>>() {
            @Override
            public void runResultOnUIThread(List<Visit> result) throws JSONException {
                if (result != null) {
                    visits.addAll(result);
                    Map<Integer, Integer> source = getSource(visits);
                    RatingsChart view = new RatingsChart(getApplicationContext(), source);
                    setContentView(view);
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.statistics_no_visits, Toast.LENGTH_LONG).show();
                }
            }
        };
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