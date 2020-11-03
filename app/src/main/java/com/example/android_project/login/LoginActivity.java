package com.example.android_project.login;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.MainActivity;
import com.example.android_project.R;
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

    private static SQLiteDatabase database;
    private static String TABLE_USERS = "users";
    private static String COLUMN_USERNAME = "username";
    private static String COLUMN_PASSWORD = "password";
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
        signIn.setOnClickListener(signInEvent());
        signUp.setOnClickListener(signUpEvent());

    }

    private View.OnClickListener signInEvent() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_username = username.getText().toString().trim();
                input_password = password.getText().toString().trim();

                if (checkAccount(input_username, input_password)) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.login_error_account),
                            Toast.LENGTH_LONG).show();
                }
            }
        };
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


    private boolean checkAccount(String username, String password) {
        // TODO SQLite

        if (connectToDatabase()) {
            if (username.equals("admin")) { // if the username exists

                if (password.equals("admin")) { // if the password is correct
                    return true;
                } else { // if the password is incorrect
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.login_incorrect_password),
                            Toast.LENGTH_LONG)
                            .show();
                    return false;
                }

            } else { // the username does not exist
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.login_error_account),
                            Toast.LENGTH_LONG)
                            .show();
                    return false;
            }

        } else {
            Toast.makeText(getApplicationContext(),
                    R.string.login_error_database,
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean connectToDatabase() {
        // TODO SQLite
        return true;
    }
}
