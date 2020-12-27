package com.example.android_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android_project.async.Callback;
import com.example.android_project.databases.model.Visit;
import com.example.android_project.adapters.VisitAdapter;
import com.example.android_project.databases.service.VisitService;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class VisitedActivity extends AppCompatActivity {


    public static final int ADD_VISIT_REQUEST_CODE = 201;
    private static final int UPDATE_VISIT_REQUEST_CODE = 300;
    public static List<Visit> visitList = new ArrayList<>();
    private Button addNewVisit;
    private CardView graph;
    private ListView list;

    private VisitService visitService;


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

        visitService = new VisitService(getApplicationContext());
        visitService.getAll(getAllFromDBCallback());
        addAdapter();

        list.setOnItemClickListener(updateVisitEventListener());
        list.setOnItemLongClickListener(deleteVisitEventListener());

    }


    private Callback<List<Visit>> getAllFromDBCallback() {
        return new Callback<List<Visit>>() {
            @Override
            public void runResultOnUIThread(List<Visit> result) throws JSONException {
                if (result != null) {
                    visitList.clear();
                    visitList.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Visit> insertIntoDBCallback() {
        return new Callback<Visit>() {
            @Override
            public void runResultOnUIThread(Visit result) throws JSONException {
                if (result != null) {
                    visitList.add(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Visit> updateToDBCallback() {
        return new Callback<Visit>() {
            @Override
            public void runResultOnUIThread(Visit result) throws JSONException {
                if (result != null) {
                    for (Visit visit : visitList) {

                        if (visit.getId() == result.getId()) {
                            visit.setAttraction(result.getAttraction());
                            visit.setDate(result.getDate());
                            visit.setRating(result.getRating());
                            break;
                        }
                    }
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Integer> deleteToDBCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUIThread(Integer result) throws JSONException {
                if (result != -1) {
                    visitList.remove(position);
                    notifyAdapter();
                }
            }
        };
    }


    private void addAdapter() {
        VisitAdapter adapter = new VisitAdapter(getApplicationContext(),
                R.layout.activity_visited_row_item,
                visitList, getLayoutInflater());
        list.setAdapter(adapter);
    }

    private AdapterView.OnItemClickListener updateVisitEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddVisitedActivity.class);
                intent.putExtra(AddVisitedActivity.VISIT_KEY, visitList.get(position));
                startActivityForResult(intent, UPDATE_VISIT_REQUEST_CODE);
            }
        };
    }


    private AdapterView.OnItemLongClickListener deleteVisitEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "DELETE",
                        Toast.LENGTH_LONG).show();
                visitService.delete(visitList.get(position), deleteToDBCallback(position));
                return true;
            }
        };
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
        Visit visit = (Visit) data.getSerializableExtra(AddVisitedActivity.VISIT_KEY);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == ADD_VISIT_REQUEST_CODE) {
                visitService.insert(visit, insertIntoDBCallback());
            } else if (requestCode == UPDATE_VISIT_REQUEST_CODE) {
                visitService.update(visit, updateToDBCallback());
            }
        }
    }
}