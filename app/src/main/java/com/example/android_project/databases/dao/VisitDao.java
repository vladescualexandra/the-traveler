package com.example.android_project.databases.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_project.databases.model.Visit;

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

    @Query("SELECT MIN(rating) from visits")
    int getMin();

    @Query("SELECT MAX(rating) from visits")
    int getMax();

    @Query("SELECT AVG(rating) from visits")
    int getAvg();


}
