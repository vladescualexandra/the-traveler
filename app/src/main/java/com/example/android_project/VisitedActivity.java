package com.example.android_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android_project.async.Callback;
import com.example.android_project.databases.model.Spending;
import com.example.android_project.databases.model.Visit;
import com.example.android_project.adapters.VisitAdapter;
import com.example.android_project.databases.service.SpendingService;
import com.example.android_project.databases.service.VisitService;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class VisitedActivity extends AppCompatActivity {


    public static final int ADD_VISIT_REQUEST_CODE = 201;
    private static final int UPDATE_VISIT_REQUEST_CODE = 300;

    public static List<Visit> visitList;
    public static List<Spending> spendingList;
    private Button addNewVisit;
    private ListView list;

    private VisitService visitService;
    private SpendingService spendingService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited);

        initComponents();

    }

    private void initComponents() {

        addNewVisit = findViewById(R.id.visted_add_new_visit);
        addNewVisit.setOnClickListener(addNewVisitEvent());

        visitList = new ArrayList<>();
        spendingList = new ArrayList<>();

        list = findViewById(R.id.visited_listView);
        addAdapter();

        String user = getSharedPreferences(StartActivity.USER_KEY, MODE_PRIVATE).getString(StartActivity.ID, null);

        visitService = new VisitService(getApplicationContext());
        visitService.getAll(user, getAllVisitsFromDBCallback());

        spendingService = new SpendingService(getApplicationContext());
        spendingService.getAll(user, getAllSpendingsFromDBCallback());


        list.setOnItemClickListener(updateVisitEventListener());
        list.setOnItemLongClickListener(deleteVisitEventListener());

    }


    private Callback<List<Visit>> getAllVisitsFromDBCallback() {
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

    private Callback<List<Spending>> getAllSpendingsFromDBCallback() {
        return new Callback<List<Spending>>() {
            @Override
            public void runResultOnUIThread(List<Spending> result) throws JSONException {
                if (result != null) {
                    spendingList.clear();
                    spendingList.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Visit> insertVisitIntoDBCallback() {
        return new Callback<Visit>() {
            @Override
            public void runResultOnUIThread(Visit result) {
                if (result != null) {
                    visitList.add(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Spending> insertSpendingIntoDBCallback() {
        return new Callback<Spending>() {
            @Override
            public void runResultOnUIThread(Spending result) throws JSONException {
                if (result != null) {
                    spendingList.add(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Visit> updateVisitToDBCallback() {
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

    private Callback<Spending> updateSpendingToDBCallback() {
        return new Callback<Spending>() {
            @Override
            public void runResultOnUIThread(Spending result) throws JSONException {
                if (result != null) {
                    for (Spending spending : spendingList) {

                        if (spending.getId() == result.getId()) {
                            spending.setVisit(result.getVisit());
                            spending.setAmount(result.getAmount());
                            break;
                        }
                    }
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Integer> deleteVisitFromDBCallback(final int position) {
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

    private Callback<Integer> deleteSpendingFromDBCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUIThread(Integer result) throws JSONException {
                if (result != -1) {
                    spendingList.remove(position);
                    notifyAdapter();
                }
            }
        };
    }


    private void addAdapter() {
        VisitAdapter adapter = new VisitAdapter(getApplicationContext(),
                R.layout.activity_visited_row_item,
                visitList,
                spendingList,
                getLayoutInflater());
        list.setAdapter(adapter);
    }

    private AdapterView.OnItemClickListener updateVisitEventListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddVisitedActivity.class);
                intent.putExtra(AddVisitedActivity.VISIT_KEY, visitList.get(position));
                intent.putExtra(AddVisitedActivity.SPENDING_KEY, spendingList.get(position));
                startActivityForResult(intent, UPDATE_VISIT_REQUEST_CODE);
            }
        };
    }


    private AdapterView.OnItemLongClickListener deleteVisitEventListener() {
        return new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                spendingService.delete(spendingList.get(position), deleteSpendingFromDBCallback(position));
                visitService.delete(visitList.get(position), deleteVisitFromDBCallback(position));
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
        Spending spending = (Spending) data.getSerializableExtra(AddVisitedActivity.SPENDING_KEY);


        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == ADD_VISIT_REQUEST_CODE) {
                visitService.insert(visit, insertVisitIntoDBCallback());
                try {
                    Thread.sleep(1000);
                    spendingService.insert(spending, visit.getId(), insertSpendingIntoDBCallback());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == UPDATE_VISIT_REQUEST_CODE) {
                visitService.update(visit, updateVisitToDBCallback());
                spendingService.update(spending, updateSpendingToDBCallback());
            }
        }
    }
}