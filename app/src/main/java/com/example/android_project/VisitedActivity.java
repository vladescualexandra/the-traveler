package com.example.android_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android_project.data.Visit;
import com.example.android_project.data.VisitAdapter;
import com.example.android_project.users.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class VisitedActivity extends AppCompatActivity {


    public static final int ADD_VISIT_REQUEST_CODE = 201;
    public static List<Visit> visitList;
    private Button addNewVisit;
    private CardView graph;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited);

        initComponents();

    }

    private void initComponents() {

        addNewVisit = findViewById(R.id.visted_add_new_visit);
        addNewVisit.setOnClickListener(addNewVisitEvent());

        graph = findViewById(R.id.visited_cardView_graph);

        list = findViewById(R.id.visited_listView);


        addAdapter();
    }

    private void addAdapter() {
        visitList = new ArrayList<>();

        VisitAdapter adapter = new VisitAdapter(getApplicationContext(),
                R.layout.activity_visited_row_item,
                visitList, getLayoutInflater());
        list.setAdapter(adapter);
    }

    private void notifyAdapter() {
        VisitAdapter adapter = (VisitAdapter) list.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private View.OnClickListener addNewVisitEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddVisitedActivity.class);
                startActivityForResult(intent, ADD_VISIT_REQUEST_CODE);
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Visit visit = (Visit) data.getSerializableExtra(AddVisitedActivity.VISIT_KEY);
            visitList.add(visit);
            notifyAdapter();
        }
    }
}