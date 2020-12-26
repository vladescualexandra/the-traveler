package com.example.android_project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.Firebase.FirebaseService;
import com.example.android_project.users.UserAccount;
import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    Intent intent;
    FirebaseService firebaseService;

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
        firebaseService = FirebaseService.getInstance();
    }

    private View.OnClickListener createAccountEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_username = username.getText().toString().trim();
                String input_email = email.getText().toString().trim();
                String input_password = password.getText().toString().trim();

                UserAccount user = new UserAccount(null, input_username, input_email, input_password);

                if (firebaseService.upsert(user)) {
                    Toast.makeText(getApplicationContext(),
                            user.getId(),
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
        };
    }

    private void returnToLogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}