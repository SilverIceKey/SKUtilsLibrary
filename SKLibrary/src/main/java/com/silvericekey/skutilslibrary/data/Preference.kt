package com.silvericekey.skutilslibrary.data

import android.content.Context
import android.content.SharedPreferences
import com.silvericekey.skutilslibrary.SKUtilsLibrary
import com.silvericekey.skutilslibrary.base.BaseApplication
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 持久化存储
 * 样式 var setXXX: TYPE by Preference(KEY, default type)
 */
class Preference<T>(val name: String, val default: T) : ReadWriteProperty<Any?, T> {
    companion object {
        val prefs: SharedPreferences by lazy {
            BaseApplication.getApp().getSharedPreferences("PreferencesData", Context.MODE_PRIVATE)
        }

        /**
         * 清除
         */
        fun clear() {
            prefs.edit().clear().apply()
        }

        /**
         * 删除某Key的值
         */
        fun remove(key: String) {
            prefs.edit().remove(key).apply()
        }
    }

    private fun putSharedPreferences(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("Sharedprefences can't be save this type")
        }.apply()
    }

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    private fun getSharedPreferences(name: String, default: T): T = with(prefs) {
        return when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("Sharedprefences can't be get this type")
        } as T
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getSharedPreferences(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putSharedPreferences(name, value)
    }
}