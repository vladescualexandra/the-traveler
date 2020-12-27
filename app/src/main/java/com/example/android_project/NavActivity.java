package com.example.android_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NavActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private static final String USER_KEY = "user_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_nav);

        configNavigation();
        initComponents();
//
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
    }

    public void configNavigation() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar, // toolbar
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void initComponents() {
        navigationView = findViewById(R.id.nav_view);
        //atasare eveniment de click pe optiunile din meniul lateral
        navigationView.setNavigationItemSelectedListener(addNavigationMenuItemSelectedEvent());
    }

    public NavigationView.OnNavigationItemSelectedListener addNavigationMenuItemSelectedEvent() {
        return new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                openActivityFromNavMenu(getApplicationContext(), item.getItemId());

                //inchidem meniul lateral
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    public static Intent openActivityFromNavMenu(Context context, int id) {
        Intent intent = null;


        if (id == R.id.nav_home) {
            intent = new Intent(context, MainActivity.class);
        } else if (id == R.id.nav_account) {
            intent = new Intent(context, AccountActivity.class);
        } else if (id == R.id.nav_favorites) {
            intent = new Intent(context, FavoritesActivity.class);
        } else if (id == R.id.nav_visited) {
            intent = new Intent(context, VisitedActivity.class);
        }

        return intent;

    }

}