package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.android_project.async.Callback;
import com.example.android_project.databases.model.Visit;
import com.example.android_project.databases.service.SpendingService;
import com.example.android_project.databases.service.VisitService;

import org.json.JSONException;

import java.util.List;

public class ReportActivity extends AppCompatActivity {

    TextView minRating;
    TextView maxRating;
    TextView avgRating;
    TextView minSpending;
    TextView maxSpending;
    TextView avgSpending;
    TextView totalSpending;

    private VisitService visitService;
    private SpendingService spendingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        initComponents();
        getDataFromDB();
    }


    private void initComponents() {
        minRating = findViewById(R.id.report_ratings_min);
        maxRating = findViewById(R.id.report_ratings_max);
        avgRating = findViewById(R.id.report_ratings_avg);
        minSpending = findViewById(R.id.report_spendings_min);
        maxSpending = findViewById(R.id.report_spendings_max);
        avgSpending = findViewById(R.id.report_spendings_avg);
        totalSpending = findViewById(R.id.report_spendings_total);

        ScrollView layout = findViewById(R.id.activity_report);
        AccountActivity.changeTheme(layout, setTheme());
    }


    private boolean setTheme() {
        return getSharedPreferences(AccountActivity.SETTINGS, MODE_PRIVATE)
                .getBoolean(AccountActivity.THEME, false);
    }


    private void getDataFromDB() {
        visitService = new VisitService(getApplicationContext());
        spendingService = new SpendingService(getApplicationContext());

        String user = getSharedPreferences(StartActivity.USER_KEY, MODE_PRIVATE).getString(StartActivity.ID, null);

        visitService.getMin(user, getMinRatingFromDBCallback());
        visitService.getMax(user, getMaxRatingFromDBCallback());
        visitService.getAvg(user, getAvgRatingFromDBCallback());

        spendingService.getMin(user, getMinSpendingFromDBCallback());
        spendingService.getMax(user, getMaxSpendingFromDBCallback());
        spendingService.getAvg(user, getAvgSpendingFromDBCallback());
        spendingService.getTotal(user, getTotalSpendingFromDBCallback());
    }


    private Callback<Integer> getMinRatingFromDBCallback() {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUIThread(Integer result) throws JSONException {
                if (result != null) {
                    minRating.setText(getString(R.string.report_rating_min, result));
                } else {
                    minRating.setText(getString(R.string.report_rating_min, -1));
                }
            }
        };
    }

    private Callback<Integer> getMaxRatingFromDBCallback() {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUIThread(Integer result) throws JSONException {
                if (result != null) {
                    maxRating.setText(getString(R.string.report_rating_max, result));
                } else {
                    maxRating.setText(getString(R.string.report_rating_max, -1));
                }
            }
        };
    }

    private Callback<Integer> getAvgRatingFromDBCallback() {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUIThread(Integer result) throws JSONException {
                if (result != null) {
                    avgRating.setText(getString(R.string.report_rating_avg, result));
                } else {
                    avgRating.setText(getString(R.string.report_rating_avg, -1));
                }
            }
        };
    }

    private Callback<Float> getMinSpendingFromDBCallback() {
        return new Callback<Float>() {
            @Override
            public void runResultOnUIThread(Float result) throws JSONException {
                if (result != null) {
                    minSpending.setText(getString(R.string.report_spending_min, result));
                } else {
                    minSpending.setText(getString(R.string.report_spending_min, -1));
                }
            }
        };
    }

    private Callback<Float> getMaxSpendingFromDBCallback() {
        return new Callback<Float>() {
            @Override
            public void runResultOnUIThread(Float result) throws JSONException {
                if (result != null) {
                    maxSpending.setText(getString(R.string.report_spending_max, result));
                } else {
                    maxSpending.setText(getString(R.string.report_spending_max, -1));
                }
            }
        };
    }


    private Callback<Float> getAvgSpendingFromDBCallback() {
        return new Callback<Float>() {
            @Override
            public void runResultOnUIThread(Float result) throws JSONException {
                if (result != null) {
                    avgSpending.setText(getString(R.string.report_spending_avg, result));
                } else {
                    avgSpending.setText(getString(R.string.report_spending_avg, -1));
                }
            }
        };
    }

    private Callback<Float> getTotalSpendingFromDBCallback() {
        return new Callback<Float>() {
            @Override
            public void runResultOnUIThread(Float result) throws JSONException {
                if (result != null) {
                    totalSpending.setText(getString(R.string.report_spending_total, result));
                } else {
                    totalSpending.setText(getString(R.string.report_spending_total, -1));
                }
            }
        };
    }
}
