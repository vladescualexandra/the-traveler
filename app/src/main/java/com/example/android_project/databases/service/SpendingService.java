package com.example.android_project.databases.service;

import android.content.Context;
import android.util.Log;

import com.example.android_project.async.AsyncTaskRunner;
import com.example.android_project.async.Callback;
import com.example.android_project.databases.DatabaseManager;
import com.example.android_project.databases.dao.SpendingDao;
import com.example.android_project.databases.model.Spending;
import com.example.android_project.databases.model.Visit;

import java.util.List;
import java.util.concurrent.Callable;

public class SpendingService {

    private final SpendingDao spendingDao;
    private final AsyncTaskRunner taskRunner;

    public SpendingService(Context context) {
        spendingDao = DatabaseManager.getInstance(context).getSpendingDao();
        taskRunner = new AsyncTaskRunner();
    }

    public void getAll(Callback<List<Spending>> callback) {
        Callable<List<Spending>> callable = new Callable<List<Spending>>() {
            @Override
            public List<Spending> call() throws Exception {
                return spendingDao.getAll();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void insert(final Spending spending, long visit, Callback<Spending> callback) {
        Callable<Spending> callable = new Callable<Spending>() {
            @Override
            public Spending call() {
                if (spending == null) {
                    return null;
                } else {
                    spending.setVisit(visit);
                    long id = spendingDao.insert(spending);
                    if (id == -1) {
                        return null;
                    } else {
                        spending.setId(id);
                        return spending;
                    }
                }
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void update(final Spending spending, Callback<Spending> callback) {
        Callable<Spending> callable = new Callable<Spending>() {
            @Override
            public Spending call() throws Exception {
                if (spending == null) {
                    return null;
                }
                int count = spendingDao.update(spending);
                if (count < 1) {
                    return null;
                }
                return spending;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void delete(final Spending spending, Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if (spending == null) {
                    return -1;
                }
                return spendingDao.delete(spending);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }


    public void getMin(Callback<Float> callback) {
        Callable<Float> callable = new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                return spendingDao.getMin();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void getMax(Callback<Float> callback) {
        Callable<Float> callable = new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                return spendingDao.getMax();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void getAvg(Callback<Float> callback) {
        Callable<Float> callable = new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                return spendingDao.getAvg();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void getTotal(Callback<Float> callback) {
        Callable<Float> callable = new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                return spendingDao.getTotal();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }


}
