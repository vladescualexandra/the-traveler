package com.example.theTraveler;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.theTraveler.databases.model.UserAccount;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    Intent intent;

    TextInputEditText username;
    TextInputEditText email;
    TextInputEditText password;
    Button create;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        intent = getIntent();
        initComponents();

        create.setOnClickListener(createAccountEvent());
    }


    private void initComponents() {
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        create = findViewById(R.id.signup_create);
    }

    private View.OnClickListener createAccountEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_username = username.getText().toString().trim();
                String input_email = email.getText().toString().trim();
                String input_password = password.getText().toString().trim();

                if (validateInput(input_username, input_email, input_password)) {

                    UserAccount user = new UserAccount(null, input_username, input_email, input_password);

                    if (StartActivity.firebaseService.upsert(user)) {
                        Toast.makeText(getApplicationContext(),
                                R.string.signup_account_created,
                                Toast.LENGTH_LONG)
                                .show();
                        returnToLogin();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                R.string.signup_account_failed,
                                Toast.LENGTH_LONG)
                                .show();
                    }
                }
            }
        };
    }

    private boolean validateInput(String input_username, String input_email, String input_password) {
        if (input_username.length() < 3) {
            username.setError("Username not long enough.");
            return false;
        } else {
            username.setError(null);
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(input_email).matches()) {
            email.setError("Invalid email.");
            return false;
        } else {
            email.setError(null);
        }
        if (input_password.length() < 3) {
            password.setError("Password not long enough.");
            return false;
        } else {
            password.setError(null);
        }
        return true;
    }

    private void returnToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}