package com.example.android_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.async.AsyncTaskRunner;
import com.example.android_project.async.Callback;
import com.example.android_project.databases.model.Visit;
import com.example.android_project.databases.service.FirebaseService;
import com.example.android_project.databases.model.UserAccount;

import org.json.JSONException;

import java.util.List;
import java.util.concurrent.Callable;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
    }

    private View.OnClickListener signInEvent() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_username = username.getText().toString().trim();
                input_password = password.getText().toString().trim();

                if (validateInput(input_username, input_password)) {
                    Callable<UserAccount> callable = new Callable<UserAccount>() {
                        @Override
                        public UserAccount call() throws Exception {
                            return StartActivity.firebaseService.select(input_username, input_password);
                        }
                    };

                    AsyncTaskRunner taskRunner = new AsyncTaskRunner();
                    taskRunner.executeAsync(callable, loginCallback());
                }
            }
        };
    }

    private Callback<UserAccount> loginCallback() {
        return new Callback<UserAccount>() {
            @Override
            public void runResultOnUIThread(UserAccount result) throws JSONException {
                if (result != null) {
                    Intent intent = new Intent(getApplicationContext(),
                            MainActivity.class);
                    intent.putExtra(USER_KEY, FirebaseService.user);
                    saveUser();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            R.string.login_error_account,
                            Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    private void saveUser() {
        editor = prefs.edit();
        editor.putString(StartActivity.ID, FirebaseService.user.getId());
        editor.putString(StartActivity.USERNAME, FirebaseService.user.getUsername());
        editor.putString(StartActivity.EMAIL, FirebaseService.user.getEmail());
        editor.putString(StartActivity.PASSWORD, FirebaseService.user.getPassword());
        editor.apply();
    }


    private boolean validateInput(String input_username, String input_password) {
        if (input_username.length() < 3) {
            username.setError(getString(R.string.username_not_long_enough));
            return false;
        } else {
            username.setError(null);
        }
        if (input_password.length() < 3) {
            password.setError(getString(R.string.password_not_long_enough));
            return false;
        } else {
            password.setError(null);
        }
        return true;
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
    }

}