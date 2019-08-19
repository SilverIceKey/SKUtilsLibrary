package com.silverknife.meizhi;

import android.app.Application;

import com.silvericekey.skutilslibrary.SKUtilsLibrary;
import com.silvericekey.skutilslibrary.netUtils.HttpUtils;

/**
 * Created by silverknife on 2017/12/13.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SKUtilsLibrary.init(this);
        HttpUtils.init("http://football.dooksport.com/");
    }
}
