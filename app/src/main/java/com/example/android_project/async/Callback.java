package com.example.android_project.async;

public interface Callback<R> {

    void runResultOnUIThread(R result);
}
