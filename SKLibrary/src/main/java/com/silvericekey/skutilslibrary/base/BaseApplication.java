package com.silvericekey.skutilslibrary.base;

import android.app.Application;

import androidx.multidex.MultiDexApplication;

import com.silvericekey.skutilslibrary.utils.activity.ActivityUtil;
import com.silvericekey.skutilslibrary.utils.log.LoggerHelper;

public class BaseApplication extends MultiDexApplication {
    public static Application app;
    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        registerActivityLifecycleCallbacks(new ActivityUtil());
        //初始化日志框架
        new LoggerHelper.Builder().build().init();
    }
}
