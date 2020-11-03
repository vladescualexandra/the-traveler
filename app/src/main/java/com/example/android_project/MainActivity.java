package com.example.android_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.android_project.adapters.AttractionAdapter;
import com.example.android_project.attractions.Attraction;
import com.example.android_project.attractions.Attractions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Attraction> attractionList;
    private ListView listViewAttractions;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Attractions list = new Attractions();
        attractionList = new ArrayList<Attraction>(list.getList());

        initComponents();
        initList();

        listViewAttractions.setOnItemClickListener(displayAttractionEvent());
    }

    private AdapterView.OnItemClickListener displayAttractionEvent() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),
                        AttractionActivity.class);

                intent.putExtra("ATTRACTION_KEY", attractionList.get(position));
                startActivity(intent);
            }
        };
    }


    private void initList() {
        AttractionAdapter adapter = new AttractionAdapter(getApplicationContext(),
                R.layout.activity_main_row_item, attractionList, getLayoutInflater());
        listViewAttractions.setAdapter(adapter);

    }

    private void initComponents() {
        listViewAttractions = findViewById(R.id.main_list);
    }
}

