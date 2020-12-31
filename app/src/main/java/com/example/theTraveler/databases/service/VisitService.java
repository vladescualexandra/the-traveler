package com.example.theTraveler.databases.service;

import android.content.Context;

import com.example.theTraveler.async.AsyncTaskRunner;
import com.example.theTraveler.async.Callback;
import com.example.theTraveler.databases.DatabaseManager;
import com.example.theTraveler.databases.dao.VisitDao;
import com.example.theTraveler.databases.model.Visit;

import java.util.List;
import java.util.concurrent.Callable;

public class VisitService {

    private final VisitDao visitDao;
    private final AsyncTaskRunner taskRunner;

    public VisitService(Context context) {
        visitDao = DatabaseManager.getInstance(context).getVisitDao();
        taskRunner = new AsyncTaskRunner();
    }

    public void getAll(String user, Callback<List<Visit>> callback) {
        Callable<List<Visit>> callable = new Callable<List<Visit>>() {
            @Override
            public List<Visit> call() throws Exception {
                return visitDao.getAll(user);
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

    public void getMin(String user, Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return visitDao.getMin(user);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void getMax(String user, Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return visitDao.getMax(user);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void getAvg(String user, Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return visitDao.getAvg(user);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
