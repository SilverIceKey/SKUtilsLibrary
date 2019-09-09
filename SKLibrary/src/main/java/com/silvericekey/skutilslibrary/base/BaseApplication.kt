package com.silvericekey.skutilslibrary.base

import android.app.Application

open class BaseApplication : Application() {
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