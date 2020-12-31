package com.example.theTraveler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.theTraveler.databases.service.FirebaseService;
import com.example.theTraveler.databases.model.UserAccount;

public class StartActivity extends AppCompatActivity {

    private static SharedPreferences userInfo;
    private static SharedPreferences.Editor editor;
    private static Intent intent;
    private static UserAccount user;
    private static final String LOGOUT_KEY = "logout_key";
    public static final String USER_KEY = "user_key";
    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    public static FirebaseService firebaseService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        intent = getIntent();
        firebaseService = FirebaseService.getInstance();

        userInfo = getSharedPreferences(USER_KEY, MODE_PRIVATE);
        String id = userInfo.getString(ID, null);
        String username = userInfo.getString(USERNAME, null);
        String email = userInfo.getString(EMAIL, null);
        String password = userInfo.getString(PASSWORD, null);

        if (id == null || username == null || email == null || password == null) {
            intent = new Intent(getApplicationContext(), LoginActivity.class);
        } else {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra(USER_KEY, new UserAccount(id, username, email, password));
        }
        startActivity(intent);
    }

}
