package com.example.android_project.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_project.MainActivity;
import com.example.android_project.R;
import com.example.android_project.databases.model.Visit;

import java.util.List;

public class VisitAdapter extends ArrayAdapter<Visit> {

    private Context context;
    private int resource;
    private List<Visit> list;
    private LayoutInflater layoutInflater;

    public VisitAdapter(@NonNull Context context,
                             int resource,
                             @NonNull List<Visit> list,
                             LayoutInflater layoutInflater) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        Visit visit = list.get(position);
        View view = layoutInflater.inflate(resource, parent, false);

        if (visit != null) {
            setAttraction(view, visit.getAttraction());
            setDate(view, visit.getDate());
            setRating(view, visit.getRating());
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



    private void addTextViewContent(TextView textView, String value) {
        if (value != null && !value.isEmpty()) {
            textView.setText(value);
        } else {
            textView.setText(R.string.default_text);
        }
    }


}
