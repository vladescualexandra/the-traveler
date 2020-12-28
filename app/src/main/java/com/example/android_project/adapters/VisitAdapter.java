package com.example.android_project.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_project.MainActivity;
import com.example.android_project.R;
import com.example.android_project.databases.model.Spending;
import com.example.android_project.databases.model.Visit;

import java.util.List;

public class VisitAdapter extends ArrayAdapter<Visit> {

    private Context context;
    private int resource;
    private List<Visit> visits;
    private List<Spending> spendings;
    private LayoutInflater layoutInflater;

    public VisitAdapter(@NonNull Context context,
                        int resource,
                        @NonNull List<Visit> list,
                        @NonNull List<Spending> spendings,
                        LayoutInflater layoutInflater) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.visits = list;
        this.spendings = spendings;
        this.layoutInflater = layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);


        if (visits.size() > 0) {
            if (position < visits.size()) {
                Visit visit = visits.get(position);
                if (visit != null) {
                    setAttraction(view, visit.getAttraction());
                    setDate(view, visit.getDate());
                    setRating(view, visit.getRating());
                }
            }
        }

        if (spendings.size() > 0) {
            if (position < spendings.size()) {
                Spending spending = spendings.get(position);
                if (spending != null) {
                    setSpending(view, spending.getAmount());
                }
            }
        }


        return view;
    }


    private void setRating(View view, int rating) {
        RatingBar rb = view.findViewById(R.id.visited_row_item_rating);
        rb.setRating((float) rating);
    }

    private void setDate(View view, String date) {
        TextView tv = view.findViewById(R.id.visited_row_item_date);
        addTextViewContent(tv, date);
    }

    private void setAttraction(View view, int attraction) {

        String name = MainActivity.attractionList.get(attraction).getName();

        TextView tv = view.findViewById(R.id.visited_row_item_attraction);
        addTextViewContent(tv, name);
    }

    private void setSpending(View view, double spending) {
        String amount = spending + "€";
        TextView tv = view.findViewById(R.id.visited_row_item_spending);
        addTextViewContent(tv, amount);
    }


    private void addTextViewContent(TextView textView, String value) {
        if (value != null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.default_text);
        }
    }


}
