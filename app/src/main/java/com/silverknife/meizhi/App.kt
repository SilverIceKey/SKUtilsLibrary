package com.silverknife.meizhi

import com.silvericekey.skutilslibrary.base.BaseApplication

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
    }

}
