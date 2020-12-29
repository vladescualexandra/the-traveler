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

    @Query("select * from spendings")
    List<Spending> getAll();

    @Insert
    long insert(Spending visit);

    @Update
    int update(Spending visit);


    @Delete
    int delete(Spending visit);

    @Query("SELECT MIN(amount) from spendings")
    float getMin();

    @Query("SELECT MAX(amount) from spendings")
    Float getMax();

    @Query("SELECT AVG(amount) from spendings")
    Float getAvg();

    @Query("SELECT SUM(amount) from spendings")
    Float getTotal();

}
