package com.example.android_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.Firebase.FirebaseService;
import com.example.android_project.async.Callback;
import com.example.android_project.users.UserAccount;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {



    EditText username;
    EditText password;
    Button signIn;
    TextView signUp;

    String input_username;
    String input_password;

    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;
    private static final String USER_KEY = "user_key";
    private static UserAccount user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
    }

    private void login() {
        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        intent.putExtra(USER_KEY, FirebaseService.user);

        editor.putString(StartActivity.ID, FirebaseService.user.getId());
        editor.putString(StartActivity.USERNAME, FirebaseService.user.getUsername());
        editor.putString(StartActivity.EMAIL, FirebaseService.user.getEmail());
        editor.putString(StartActivity.PASSWORD, FirebaseService.user.getPassword());
        editor.apply();

        startActivity(intent);
    }


    private View.OnClickListener signInEvent() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_username = username.getText().toString().trim();
                input_password = password.getText().toString().trim();

                StartActivity.firebaseService.select(input_username, input_password);

                if (FirebaseService.user != null) {
                    synchronized (FirebaseService.user) {
                        login();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.login_error_account),
                            Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private View.OnClickListener signUpEvent() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        };
    }

    private void initComponents() {
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        signIn = findViewById(R.id.login_signIn);
        signUp = findViewById(R.id.login_signUp);

        signIn.setOnClickListener(signInEvent());
        signUp.setOnClickListener(signUpEvent());

        prefs = getSharedPreferences(USER_KEY, MODE_PRIVATE);
        editor = prefs.edit();

    }

}