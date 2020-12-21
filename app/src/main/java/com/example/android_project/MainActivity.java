package com.example.android_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.android_project.async.AsyncTaskRunner;
import com.example.android_project.async.Callback;
import com.example.android_project.data.AttractionAdapter;
import com.example.android_project.data.Attraction;
import com.example.android_project.data.AttractionsJSONParser;
import com.example.android_project.network.HttpManager;
import com.example.android_project.users.UserAccount;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    private List<Attraction> attractionList = new ArrayList<>();
    private static ProgressBar progressBar;
    private static ListView listViewAttractions;
    private static final String ATTRACTION_KEY = "attraction_key";
    private DrawerLayout drawerLayout;

    private static Intent intent;
    private static UserAccount user;
    private static final String USER_KEY = "user_key";
    private static SharedPreferences userInfo;

    private static final String URL_ATTRACTIONS = "https://jsonkeeper.com/b/25J5";
    private final AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        initComponents();
        getAttractionsFromUrl();
        setNavigationView();
    }

    private void setNavigationView() {
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                } else if (item.getItemId() == R.id.nav_account) {
                    intent = new Intent(getApplicationContext(), AccountActivity.class);
                } else if (item.getItemId() == R.id.nav_favorites) {
                    intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                } else if (item.getItemId() == R.id.nav_visited) {
                    intent = new Intent(getApplicationContext(), VisitedActivity.class);
                }
                intent.putExtra(USER_KEY, user);
                startActivity(intent);
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        userInfo = getSharedPreferences(USER_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();
        editor.putInt(USER_KEY, user.getId());
        editor.apply();
    }

    private AdapterView.OnItemClickListener displayAttractionEvent() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TEST", attractionList.get(position).getName());

                Intent atr = new Intent(getApplicationContext(),
                        AttractionActivity.class);

//                atr.putExtra(ATTRACTION_KEY, (Serializable) attractionList.get(position));
                atr.putExtra(ATTRACTION_KEY, (Serializable) attractionList.get(position));
                startActivity(atr);
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        userInfo = getSharedPreferences(USER_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();
        editor.putInt(USER_KEY, user.getId());
        editor.apply();
    }

    private void initList() {
        AttractionAdapter adapter = new AttractionAdapter(getApplicationContext(),
                R.layout.activity_main_row_item, attractionList, getLayoutInflater());
        listViewAttractions.setAdapter(adapter);
        listViewAttractions.setOnItemClickListener(displayAttractionEvent());

    }


    public static void notifyAdapter() {
        AttractionAdapter adapter = (AttractionAdapter) listViewAttractions.getAdapter();
        adapter.notifyDataSetChanged();
        listViewAttractions.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void getAttractionsFromUrl() {
        Callable<String> asyncOperation = new HttpManager(URL_ATTRACTIONS);
        Callback<String> mainThreadOperation = mainThreadOperationGetAttractionsFromUrl();
        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> mainThreadOperationGetAttractionsFromUrl() {
        return (result) -> {
            attractionList.addAll(AttractionsJSONParser.retrieveData(result));
            MainActivity.notifyAdapter();
        };
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private void initComponents() {

        intent = getIntent();
        user = (UserAccount) intent.getSerializableExtra(USER_KEY);
        SharedPreferences prefs = getSharedPreferences(USER_KEY, 0);
        int userID = prefs.getInt(USER_KEY, -1);

        if (userID >= 0) {
            user = UserAccount.getUserAccountByID(userID);
        }

        progressBar = findViewById(R.id.main_progressBar);
        listViewAttractions = findViewById(R.id.main_list);
        listViewAttractions.setVisibility(View.INVISIBLE);
        initList(); // add adapter
    }


}

