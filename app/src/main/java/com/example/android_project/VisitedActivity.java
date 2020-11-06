package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.android_project.users.UserAccount;

public class VisitedActivity extends AppCompatActivity {


    private static Intent intent;
    private static UserAccount user;
    private static final String USER_KEY = "user_key";
    private static SharedPreferences userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visited);

        intent = getIntent();
        user = (UserAccount) intent.getSerializableExtra(USER_KEY);
        SharedPreferences prefs = getSharedPreferences(USER_KEY, 0);
        int userID = prefs.getInt(USER_KEY, -1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (user != null) {
            userInfo = getSharedPreferences(USER_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = userInfo.edit();
            editor.putInt(USER_KEY, user.getId());
            editor.apply();
        }
    }

}