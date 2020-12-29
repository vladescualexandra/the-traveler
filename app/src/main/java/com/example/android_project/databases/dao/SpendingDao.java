package com.example.android_project.databases.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_project.databases.model.Spending;
import com.example.android_project.databases.model.Visit;

import java.util.List;

@Dao
public interface SpendingDao {

    @Query("select * from spendings where user LIKE :user")
    List<Spending> getAll(String user);

    @Insert
    long insert(Spending visit);

    @Update
    int update(Spending visit);


    @Delete
    int delete(Spending visit);

    @Query("SELECT MIN(amount) from spendings where user LIKE :user")
    float getMin(String user);

    @Query("SELECT MAX(amount) from spendings where user LIKE :user")
    Float getMax(String user);

    @Query("SELECT AVG(amount) from spendings where user LIKE :user")
    Float getAvg(String user);

    @Query("SELECT SUM(amount) from spendings where user LIKE :user")
    Float getTotal(String user);

}
