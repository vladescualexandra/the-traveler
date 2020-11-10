package com.example.android_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.users.UserAccount;

public class StartActivity extends AppCompatActivity {

    private static SharedPreferences userInfo;
    private static Intent intent;
    private static UserAccount user;
    private static final String LOGOUT_KEY = "logout_key";
    public static final String USER_KEY = "user_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        intent = getIntent();
        int key = intent.getIntExtra(LOGOUT_KEY, 0);

        if (key == -1) {
            user = null;

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        } else {

            userInfo = getSharedPreferences(USER_KEY, 0);
            int userID = userInfo.getInt(USER_KEY, -1);

            if (userID > 0) {

                user = UserAccount.getUserAccountByID(userID);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(USER_KEY, user);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }

        }

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
