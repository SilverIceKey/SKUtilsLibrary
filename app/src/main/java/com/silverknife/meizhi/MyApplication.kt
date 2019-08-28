package com.silverknife.meizhi

import android.app.Application
import android.view.Gravity

import com.silvericekey.skutilslibrary.SKUtilsLibrary
import com.silvericekey.skutilslibrary.utils.HttpUtil

/**
 * Created by silverknife on 2017/12/13.
 */

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SKUtilsLibrary.init(this)
        HttpUtil.init("http://football.dooksport.com/")
    }
}
