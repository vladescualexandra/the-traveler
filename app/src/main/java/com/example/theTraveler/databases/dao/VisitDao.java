package com.example.theTraveler.databases.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.theTraveler.databases.model.Visit;

import java.util.List;

@Dao
public interface VisitDao {

    @Query("SELECT * from visits where user LIKE :user")
    List<Visit> getAll(String user);


    @Insert
    long insert(Visit visit);


    @Update
    int update(Visit visit);


    @Delete
    int delete(Visit visit);

    @Query("SELECT MIN(rating) from visits where user LIKE :user")
    int getMin(String user);

    @Query("SELECT MAX(rating) from visits where user LIKE :user")
    int getMax(String user);

    @Query("SELECT AVG(rating) from visits where user LIKE :user")
    int getAvg(String user);


}
