package com.example.theTraveler.async;

import org.json.JSONException;

public interface Callback<R> {

    void runResultOnUIThread(R result) throws JSONException;
}
