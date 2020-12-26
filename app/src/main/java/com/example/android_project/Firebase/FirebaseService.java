package com.example.android_project.Firebase;

import androidx.annotation.NonNull;

import com.example.android_project.users.UserAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseService {

    public static final String USERS_TABLE_NAME = "users";
    private DatabaseReference database;

    private static FirebaseService firebaseService;

    private FirebaseService() {
        database = FirebaseDatabase.getInstance().getReference(USERS_TABLE_NAME);
    }

    public static FirebaseService getInstance() {
        if (firebaseService == null) {
            synchronized (FirebaseService.class) {
                if (firebaseService == null) {
                    firebaseService = new FirebaseService();
                }
            }
        }
        return firebaseService;
    }

    public boolean upsert(@NonNull UserAccount user) {
        if (user.getId() == null || user.getId().trim().isEmpty()) {
            String id = database.push().getKey();
            user.setId(id);
        }

        database.child(user.getId()).setValue(user);
        return true;
    }

}
