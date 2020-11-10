package com.example.android_project.async;

import android.os.Handler;
import java.util.concurrent.Callable;

public class RunnableTask<R> implements Runnable {

    private final Callable<R> asyncOperation;
    private final Handler handler;
    private final Callback<R> mainThreadOperation;

    public RunnableTask(Callable<R> asyncOperation,
                            Handler handler,
                            Callback<R> mainThreadOperation) {

        this.asyncOperation = asyncOperation;
        this.handler = handler;
        this.mainThreadOperation = mainThreadOperation;

    }

    @Override
    public void run() {
        try {
            final R result = asyncOperation.call();
            handler.post(new HandlerMessage<R>(mainThreadOperation, result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
