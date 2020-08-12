package com.silvericekey.skutilslibrary.base

import androidx.multidex.MultiDexApplication
import com.silvericekey.skutilslibrary.SKUtilsLibrary
import io.realm.Realm
import io.realm.RealmConfiguration

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
        SKUtilsLibrary.init(app!!)
        Realm.init(this)
        Realm.setDefaultConfiguration(
                RealmConfiguration.Builder().name("realmData")
                        .deleteRealmIfMigrationNeeded()
                        .build()
        )
    }

    open fun statusChange() {

    }
}