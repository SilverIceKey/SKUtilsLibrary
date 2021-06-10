package com.silverknife.meizhi

import com.orhanobut.logger.Logger
import com.silvericekey.skutilslibrary.base.BaseApplication
import com.silvericekey.skutilslibrary.net.RetrofitClient
import com.silvericekey.skutilslibrary.utils.log.LoggerHelper
import com.silverknife.meizhi.common.RetrofitDefaultConfig

/**
 * Created by silverknife on 2017/12/13.
 */

class App : BaseApplication() {
    companion object{
        @JvmStatic
        private var mApp:App?=null
        @JvmStatic
        fun getInstance():App{
            return mApp!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        mApp = this
        LoggerHelper.Builder().build().init()
        Logger.d("初始化")
        RetrofitClient.getInstance().defaultConfig(RetrofitDefaultConfig())
    }

}
