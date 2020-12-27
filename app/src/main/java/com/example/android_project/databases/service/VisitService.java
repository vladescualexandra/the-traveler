package com.example.android_project.databases.service;

import android.content.Context;
import android.util.Log;

import com.example.android_project.async.AsyncTaskRunner;
import com.example.android_project.async.Callback;
import com.example.android_project.databases.DatabaseManager;
import com.example.android_project.databases.dao.VisitDao;
import com.example.android_project.databases.model.Visit;

import java.util.List;
import java.util.concurrent.Callable;

public class VisitService {

    private final VisitDao visitDao;
    private final AsyncTaskRunner taskRunner;

    public VisitService(Context context) {
        visitDao = DatabaseManager.getInstance(context).getVisitDao();
        taskRunner = new AsyncTaskRunner();
    }

    public void getAll(Callback<List<Visit>> callback) {
        Callable<List<Visit>> callable = new Callable<List<Visit>>() {
            @Override
            public List<Visit> call() throws Exception {
                return visitDao.getAll();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void insert(final Visit visit, Callback<Visit> callback) {
        Callable<Visit> callable = new Callable<Visit>() {
            @Override
            public Visit call() {
                if (visit == null) {
                    return null;
                } else {
                    long id = visitDao.insert(visit);
                    Log.e("id", String.valueOf(id));
                    if (id == -1) {
                        return null;
                    } else {
                        visit.setId(id);
                        return visit;
                    }
                }
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void update(final Visit visit, Callback<Visit> callback) {
        Callable<Visit> callable = new Callable<Visit>() {
            @Override
            public Visit call() throws Exception {
                if (visit == null) {
                    return null;
                }
                int count = visitDao.update(visit);
                if (count < 1) {
                    return null;
                }
                return visit;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void delete(final Visit visit, Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if (visit == null) {
                    return -1;
                }
                return visitDao.delete(visit);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
