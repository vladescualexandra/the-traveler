package com.example.android_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.databases.service.FirebaseService;
import com.example.android_project.databases.model.UserAccount;

public class AccountActivity extends AppCompatActivity {

    public static final String SETTINGS = "settiings";
    public static final String THEME = "theme";
    public static final String NOTIFICATIONS = "notifications";
    private static Intent intent;
    private static final String USER_KEY = "user_key";
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;
    private static UserAccount user;


    private TextView username;
    private TextView email;
    private Switch notifications;
    private Switch theme;
    private TextView logout;

    private static final String LOGOUT_KEY = "logout_key";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initComponents();


    }

    private View.OnClickListener logoutEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseService.user = null;

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                SharedPreferences userInfo = getSharedPreferences(USER_KEY, MODE_PRIVATE);
                editor = userInfo.edit();
                editor.putString(StartActivity.ID, null);
                editor.putString(StartActivity.USERNAME, null);
                editor.putString(StartActivity.EMAIL, null);
                editor.putString(StartActivity.PASSWORD, null);
                editor.apply();

                startActivity(intent);
            }
        };
    }

    private CompoundButton.OnCheckedChangeListener setThemeEvent() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(THEME, isChecked);
                editor.apply();
            }
        };
    }

    private CompoundButton.OnCheckedChangeListener setNotificationsEvent() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(NOTIFICATIONS, isChecked);
                editor.apply();
            }
        };
    }

    private void initComponents() {

        username = findViewById(R.id.account_username);
        email = findViewById(R.id.account_email);
        notifications = findViewById(R.id.switch_notifications);
        theme = findViewById(R.id.switch_theme);
        logout = findViewById(R.id.account_logout);

        intent = getIntent();
        user = (UserAccount) intent.getSerializableExtra(USER_KEY);

        username.setText(user.getUsername());
        email.setText(user.getEmail());

        prefs = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        editor = prefs.edit();

        notifications.setChecked(prefs.getBoolean(NOTIFICATIONS, false));
        theme.setChecked(prefs.getBoolean(THEME, false));

        notifications.setOnCheckedChangeListener(setNotificationsEvent());
        theme.setOnCheckedChangeListener(setThemeEvent());
        logout.setOnClickListener(logoutEvent());


    }
}
