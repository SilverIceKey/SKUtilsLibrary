package com.silvericekey.skutilslibrary.base

import androidx.multidex.MultiDexApplication

open class BaseApplication : MultiDexApplication() {
    companion object {
        @JvmStatic
        private var app: BaseApplication? = null

        @JvmStatic
        fun getApp(): BaseApplication = app!!
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    open fun statusChange() {

    }
}