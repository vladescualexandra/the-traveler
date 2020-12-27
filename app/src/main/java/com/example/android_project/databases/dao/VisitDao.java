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

    @Query("SELECT * from visits")
    List<Visit> getAll();


    @Insert
    long insert(Visit visit);
    // returnează id-ul sau -1 dacă apar erori

    @Update
    int update(Visit visit);
    // trebuie să aibă id-ul populat
    // returnează numărul de rânduri afectate
    // ar trebui să afecteze un singur rând

    @Delete
    int delete(Visit visit);
    // la fel ca update

    // returneaza -1 in caz de eroare
}
