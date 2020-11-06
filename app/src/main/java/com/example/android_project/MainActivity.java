package com.example.android_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.android_project.adapters.AttractionAdapter;
import com.example.android_project.data.Attraction;
import com.example.android_project.data.Attractions;
import com.example.android_project.users.UserAccount;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Attraction> attractionList;
    private ListView listViewAttractions;
    private static final String ATTRACTION_KEY = "attraction_key";

    private static Intent intent;
    private static UserAccount user;
    private static final String USER_KEY = "user_key";
    private static SharedPreferences userInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);


        intent = getIntent();
        user = (UserAccount) intent.getSerializableExtra(USER_KEY);
        SharedPreferences prefs = getSharedPreferences(USER_KEY, 0);
        int userID = prefs.getInt(USER_KEY, -1);
        Log.i("USER ID", String.valueOf(userID));

        Attractions list = new Attractions();
        attractionList = new ArrayList<Attraction>(list.getList());

        initComponents();
        initList();

        listViewAttractions.setOnItemClickListener(displayAttractionEvent());


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra(USER_KEY, user);
                } else if (item.getItemId() == R.id.nav_account) {
                    intent = new Intent(getApplicationContext(), AccountActivity.class);
                    intent.putExtra(USER_KEY, user);
                } else if (item.getItemId() == R.id.nav_favorites) {
                    intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                    intent.putExtra(USER_KEY, user);
                } else if (item.getItemId() == R.id.nav_visited) {
                    intent = new Intent(getApplicationContext(), VisitedActivity.class);
                    intent.putExtra(USER_KEY, user);
                }
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
                Intent intent = new Intent(getApplicationContext(),
                        AttractionActivity.class);

                intent.putExtra(ATTRACTION_KEY, attractionList.get(position));
                startActivity(intent);
            }
        };
    }


    private void initList() {
        AttractionAdapter adapter = new AttractionAdapter(getApplicationContext(),
                R.layout.activity_main_row_item, attractionList, getLayoutInflater());
        listViewAttractions.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        super.onBackPressed();
    }

    private void initComponents() {
        listViewAttractions = findViewById(R.id.main_list);
    }
}

