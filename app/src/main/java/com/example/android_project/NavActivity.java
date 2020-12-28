package com.example.android_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

public class NavActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_nav);

        configNavigation();
        initComponents();
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
        navigationView.setNavigationItemSelectedListener(addNavigationMenuItemSelectedEvent());
        View headerView = navigationView.getHeaderView(0);
        TextView username = (TextView) headerView.findViewById(R.id.nav_username);
        username.setText("Hello");
    }

    public NavigationView.OnNavigationItemSelectedListener addNavigationMenuItemSelectedEvent() {
        return new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                openActivityFromNavMenu(getApplicationContext(), item.getItemId());
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
        } else if (id == R.id.nav_statistics) {
            intent = new Intent(context, StatisticsActivity.class);
        } else if (id == R.id.nav_visited) {
            intent = new Intent(context, VisitedActivity.class);
        }

        return intent;

    }

}