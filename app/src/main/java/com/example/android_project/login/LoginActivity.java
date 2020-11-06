package com.example.android_project.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.MainActivity;
import com.example.android_project.R;
import com.example.android_project.users.UserAccount;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button signIn;
    TextView signUp;

    String input_username;
    String input_password;

    private static SharedPreferences userInfo;

    private static final String USER_KEY = "user_key";
    private static UserAccount user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();

        userInfo = getSharedPreferences(USER_KEY, 0);
        int userID = userInfo.getInt(USER_KEY, -1);

        if (userID > 0) {
            user = UserAccount.getUserAccountByID(userID);
        } else {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.login_error_account),
                    Toast.LENGTH_SHORT).show();
        }



        signIn.setOnClickListener(signInEvent());
        signUp.setOnClickListener(signUpEvent());

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (user != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra(USER_KEY, user);
            startActivity(intent);
        }
    }


    private View.OnClickListener signInEvent() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_username = username.getText().toString().trim();
                input_password = password.getText().toString().trim();

                if (checkAccount(input_username, input_password) > 0) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra(USER_KEY, user);

                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.login_error_account),
                            Toast.LENGTH_LONG).show();
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
    }


    private int checkAccount(String username, String password) {
        // returns the user's id if it exits,
        // otherwise, it returns -1
        // TODO SQLite

        String usernameFromDB = "admin";
        String passwordFromDB = "admin";

        if (connectToDatabase()) {
            if (username.equals(usernameFromDB)) { // if the username exists
                if (password.equals(passwordFromDB)) { // if the password is correct
                    user = UserAccount.getUserAccountByUsername(usernameFromDB);
                    return user.getId();
                } else { // if the password is incorrect
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.login_incorrect_password),
                            Toast.LENGTH_LONG)
                            .show();
                    return -1;
                }

            } else { // the username does not exist
                Toast.makeText(getApplicationContext(),
                        getString(R.string.login_error_account),
                        Toast.LENGTH_LONG)
                        .show();
                return -1;
            }

        } else {
            Toast.makeText(getApplicationContext(),
                    R.string.login_error_database,
                    Toast.LENGTH_LONG).show();
            return -1;
        }
    }

    private boolean connectToDatabase() {
        // TODO SQLite
        return true;
    }




}
