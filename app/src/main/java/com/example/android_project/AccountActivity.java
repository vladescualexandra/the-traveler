package com.example.android_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.users.UserAccount;

import org.w3c.dom.Text;

public class AccountActivity extends AppCompatActivity {

    private static Intent intent;
    private static UserAccount user;
    private static final String USER_KEY = "user_key";
    private static SharedPreferences userInfo;


    private TextView username;
    private TextView email;
    private Switch notifications;
    private Switch theme;
    private TextView logout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        intent = getIntent();
        user = (UserAccount) intent.getSerializableExtra(USER_KEY);
        SharedPreferences prefs = getSharedPreferences(USER_KEY, 0);
        int userID = prefs.getInt(USER_KEY, -1);

        initComponents();

        notifications.setOnCheckedChangeListener(setNotificationsEvent());
        theme.setOnCheckedChangeListener(setThemeEvent());
        logout.setOnClickListener(logoutEvent());

    }

    private View.OnClickListener logoutEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    private CompoundButton.OnCheckedChangeListener setThemeEvent() {
        return  new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(),
                            "Dark theme.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Light theme.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private CompoundButton.OnCheckedChangeListener setNotificationsEvent() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(),
                            "Notifications enabled.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Notifications disabled.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
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

    private void initComponents() {
        username = findViewById(R.id.account_username);
        email = findViewById(R.id.account_email);
        notifications = findViewById(R.id.switch_notifications);
        theme = findViewById(R.id.switch_theme);
        logout = findViewById(R.id.account_logout);

        username.setText(user.getUsername());
        email.setText(user.getEmail());
    }
}
