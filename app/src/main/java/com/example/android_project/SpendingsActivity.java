package com.example.android_project;

import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.async.Callback;
import com.example.android_project.databases.model.Spending;
import com.example.android_project.databases.model.Visit;
import com.example.android_project.databases.service.SpendingService;
import com.example.android_project.databases.service.VisitService;
import com.example.android_project.util.RatingsChart;
import com.example.android_project.util.SpendingsChart;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpendingsActivity extends AppCompatActivity {


    List<Spending> spendings = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SpendingService spendingService = new SpendingService(getApplicationContext());
        String user = getSharedPreferences(StartActivity.USER_KEY, MODE_PRIVATE)
                .getString(StartActivity.ID, null);
        spendingService.getAll(user, getSpendingsCallback());

    }

    private Callback<List<Spending>> getSpendingsCallback() {
        return new Callback<List<Spending>>() {
            @Override
            public void runResultOnUIThread(List<Spending> result) throws JSONException {
                if (result != null) {
                    spendings.addAll(result);
                    List<Double> source = getSource(result);
                    SpendingsChart view = new SpendingsChart(getApplicationContext(), source);
                    setContentView(view);
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.statistics_no_visits, Toast.LENGTH_LONG).show();
                }
            }
        };
    }


    private List<Double> getSource(List<Spending> spendings) {
        if (spendings == null || spendings.isEmpty()) {
            return null;
        } else {
            List<Double> source = new ArrayList<>();

            for (Spending spending : spendings) {
                source.add(spending.getAmount());
            }
            return source;
        }
    }

}
