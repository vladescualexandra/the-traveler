package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.android_project.databases.model.UserAccount;

public class FavoritesActivity extends AppCompatActivity {

    private static Intent intent;
    private static UserAccount user;
    private static final String USER_KEY = "user_key";
    private static SharedPreferences userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        intent = getIntent();
        user = (UserAccount) intent.getSerializableExtra(USER_KEY);
        SharedPreferences prefs = getSharedPreferences(USER_KEY, 0);
        String userID = prefs.getString(USER_KEY, null);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (user != null) {
            userInfo = getSharedPreferences(USER_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = userInfo.edit();
            editor.putString(USER_KEY, user.getId());
            editor.apply();
        }
    }
}