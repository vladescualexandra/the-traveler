package com.example.android_project;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.databases.model.Spending;
import com.example.android_project.databases.model.Visit;
import com.example.android_project.util.SpendingsChart;

import java.util.ArrayList;
import java.util.List;

public class SpendingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        List<Double> source = getSource(VisitedActivity.spendingList);
        SpendingsChart spendingsChart = new SpendingsChart(getApplicationContext(), source);
        setContentView(spendingsChart);

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
