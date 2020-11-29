package com.silvericekey.skutilslibrary.base

import androidx.multidex.MultiDexApplication
import com.blankj.utilcode.util.Utils
import com.facebook.drawee.backends.pipeline.Fresco
import java.util.ArrayList

open class BaseApplication : MultiDexApplication() {
    companion object {
        @JvmStatic
        private var app: BaseApplication? = null

        @JvmStatic
        fun getApp(): BaseApplication = app!!
        var presenters: ArrayList<BasePresenter> = arrayListOf()
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        Utils.init(this)
        Fresco.initialize(this)
    }

    open fun statusChange() {

    }
}