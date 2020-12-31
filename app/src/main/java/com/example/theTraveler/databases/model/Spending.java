package com.example.theTraveler.databases.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "spendings",
        foreignKeys = @ForeignKey(entity = Visit.class,
                parentColumns = "id",
                childColumns = "visit",
                onDelete = ForeignKey.CASCADE))
public class Spending implements Serializable {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "user")
    private String user;

    @ColumnInfo(name = "visit", index = true)
    private long visit;

    @ColumnInfo(name = "amount")
    private double amount;

    public Spending(long id, String user, long visit, double amount) {
        this.id = id;
        this.user = user;
        this.visit = visit;
        this.amount = amount;
    }

    @Ignore
    public Spending(String user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    @Ignore
    public Spending() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getVisit() {
        return visit;
    }

    public void setVisit(long visit) {
        this.visit = visit;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Spending{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", visit=" + visit +
                ", amount=" + amount +
                '}';
    }
}
