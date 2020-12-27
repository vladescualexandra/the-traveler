package com.example.android_project.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android_project.databases.dao.VisitDao;
import com.example.android_project.databases.model.Visit;

@Database(entities = {Visit.class}, exportSchema = false, version = 1)
public abstract class DatabaseManager extends RoomDatabase {

    private static final String DATABASE_NAME = "android_project";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (databaseManager == null) {
                    databaseManager = Room.databaseBuilder(context,
                            DatabaseManager.class,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return databaseManager;
    }

    public abstract VisitDao getVisitDao();
}
