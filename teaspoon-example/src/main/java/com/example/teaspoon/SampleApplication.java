package com.example.teaspoon;

import android.app.Application;

import teaspoon.TeaSpoon;

public class SampleApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();

        TeaSpoon.initialize();
    }
}
