package com.example.android_project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.android_project.databases.service.FirebaseService;
import com.example.android_project.databases.model.UserAccount;

import android.graphics.Color;


public class AccountActivity extends AppCompatActivity {

    public static final String SETTINGS = "settiings";
    public static final String THEME = "theme";
    public static final String NOTIFICATIONS = "notifications";
    private static final String CHANNEL_ID = "Channel ID";
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

    ConstraintLayout layout;

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
                Toast.makeText(getApplicationContext(),
                        isChecked ? "Dark theme" : "Light theme",
                        Toast.LENGTH_SHORT).show();

                changeTheme(layout, isChecked);

            }
        };
    }

    public static void changeTheme(android.view.ViewGroup layout, boolean on) {
        if (on) {
            layout.setBackgroundColor(Color.rgb(64, 61, 88));
        } else {
            layout.setBackgroundColor(Color.WHITE);
        }
    }

    private CompoundButton.OnCheckedChangeListener setNotificationsEvent() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(NOTIFICATIONS, isChecked);
                editor.apply();
                Toast.makeText(getApplicationContext(),
                        isChecked ? "Notifications enabled" : "Notifications disabled",
                        Toast.LENGTH_SHORT).show();
                createNotificationChannel();

            }
        };
    }

    // Source: https://developer.android.com/training/notify-user/build-notification
    private void createNotificationChannel() {
        // ONLY ON API 26+
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("See what you are missing out!")
                    .setContentText("What do you want to visit next?")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            NotificationManagerCompat notificationManager2 = NotificationManagerCompat.from(this);

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, builder.build());

        }


    }

    private void initComponents() {

        username = findViewById(R.id.account_username);
        email = findViewById(R.id.account_email);
        notifications = findViewById(R.id.switch_notifications);
        theme = findViewById(R.id.switch_theme);
        logout = findViewById(R.id.account_logout);
        layout = findViewById(R.id.activity_account);


        intent = getIntent();
        user = (UserAccount) intent.getSerializableExtra(USER_KEY);

        username.setText(user.getUsername());
        email.setText(user.getEmail());

        prefs = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        editor = prefs.edit();

        notifications.setChecked(prefs.getBoolean(NOTIFICATIONS, false));
        theme.setChecked(prefs.getBoolean(THEME, false));
        changeTheme(layout, setTheme());

        notifications.setOnCheckedChangeListener(setNotificationsEvent());
        theme.setOnCheckedChangeListener(setThemeEvent());
        logout.setOnClickListener(logoutEvent());
    }

    private boolean setTheme() {
        return getSharedPreferences(AccountActivity.SETTINGS, MODE_PRIVATE)
                .getBoolean(AccountActivity.THEME, false);
    }
}
