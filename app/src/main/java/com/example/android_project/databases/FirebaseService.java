package com.example.android_project.databases;

import androidx.annotation.NonNull;

import com.example.android_project.users.UserAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseService {

    public static final String USERS_TABLE_NAME = "users";
    private DatabaseReference database;
    public static UserAccount user;

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

    public void select(@NonNull String username, @NonNull String password) {
        if (username.length() > 3 && password.length() > 3) {

            Query query = database.orderByChild("username").equalTo(username);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot item : snapshot.getChildren()) {
                        user = item.getValue(UserAccount.class);
                        user.setId(item.getKey());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}
