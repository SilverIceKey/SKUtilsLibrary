package com.silvericekey.skutilslibrary.base;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

public class BaseApplication extends MultiDexApplication {
    public static Application app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
