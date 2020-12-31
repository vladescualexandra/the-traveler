package com.example.theTraveler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Spinner;

import com.example.theTraveler.databases.model.Spending;
import com.example.theTraveler.databases.model.Visit;

import static java.lang.Integer.parseInt;

public class AddVisitedActivity extends AppCompatActivity {

    Intent intent;
    private Visit visit = new Visit();
    private Spending spending = new Spending();
    public static final String VISIT_KEY = "visit_key";
    public static final String SPENDING_KEY = "spending_key";

    private Spinner attraction;
    private EditText amount;
    private DatePicker date;
    private RatingBar rating;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_visited);

        intent = getIntent();
        initComponents();

        if (intent.hasExtra(VISIT_KEY) && intent.hasExtra(SPENDING_KEY)) {
            visit = (Visit) intent.getSerializableExtra(VISIT_KEY);
            spending = (Spending) intent.getSerializableExtra(SPENDING_KEY);
            buildViews(visit, spending);
        }
    }

    private void buildViews(Visit visit, Spending spending) {
        if (visit == null || spending == null) {
            return;
        }
        if (visit.getAttraction() >= 0) {
            attraction.setSelection(visit.getAttraction(), true);
        }
        if (visit.getDate() != null) {
            String[] pieces = visit.getDate().split("/");
            // 0 - day
            // 1 - month
            // 2 - year
            date.init(parseInt(pieces[2]), parseInt(pieces[1]), parseInt(pieces[0]), null);
        }
        if (visit.getRating() >= 0 && visit.getRating() <= 5) {
            rating.setRating(visit.getRating());
        } else {
            rating.setRating(0);
        }

        if (spending.getAmount() >= 0) {
            amount.setText(String.valueOf(spending.getAmount()));
        }

        add.setText(R.string.add_visit_save);
    }

    private void initComponents() {
        attraction = findViewById(R.id.add_visited_spinner_attractions);
        addAttractionAdapter();
        amount = findViewById(R.id.add_visited_spending);
        date = findViewById(R.id.add_visited_date);
        date.setMaxDate(System.currentTimeMillis());
        rating = findViewById(R.id.add_visited_rating);
        add = findViewById(R.id.add_visited_btn_add);

        add.setOnClickListener(addNewVisitEvent());

        ScrollView layout = findViewById(R.id.activity_add_visited);
        AccountActivity.changeTheme(layout, setTheme());
    }

    private boolean setTheme() {
        return getSharedPreferences(AccountActivity.SETTINGS, MODE_PRIVATE)
                .getBoolean(AccountActivity.THEME, false);
    }


    private void addAttractionAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                MainActivity.visitList);
        attraction.setAdapter(adapter);
    }

    private View.OnClickListener addNewVisitEvent() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateString = date.getDayOfMonth() + "/" + date.getMonth() + "/" + date.getYear();
                String userID = getSharedPreferences(StartActivity.USER_KEY, MODE_PRIVATE).getString(StartActivity.ID, null);

                visit.setUser(userID);
                visit.setAttraction((int) attraction.getSelectedItemId());
                visit.setDate(dateString);
                visit.setRating((int) rating.getRating());

                spending.setUser(userID);

                try {
                    Double money = Double.parseDouble(amount.getText().toString().trim());
                    spending.setAmount(money);
                } catch (NumberFormatException e) {
                    spending.setAmount(0.0);
                }

                intent.putExtra(VISIT_KEY, visit);
                intent.putExtra(SPENDING_KEY, spending);
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }
}