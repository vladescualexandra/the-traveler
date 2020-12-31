package com.example.theTraveler.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.theTraveler.databases.dao.SpendingDao;
import com.example.theTraveler.databases.dao.VisitDao;
import com.example.theTraveler.databases.model.Spending;
import com.example.theTraveler.databases.model.Visit;

@Database(entities = {Visit.class, Spending.class}, exportSchema = false, version = 2)
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

    public abstract SpendingDao getSpendingDao();
}
