package com.silvericekey.skutilslibrary.utils

import android.util.Log
import io.realm.Realm
import io.realm.RealmModel

/**
 * 数据库工具
 */
object RealmUtils {
    //增加或更新数据
    fun addOrUpdate(response: RealmModel) {
        try {
            Realm.getDefaultInstance().use {
                it.beginTransaction()
                it.insertOrUpdate(response)
                it.commitTransaction()
                if (!it.isClosed) {
                    it.close()
                }
            }
        } catch (e: Exception) {
            Log.e("realm",e.toString())
        }
    }
    //获取第一条数据
    fun <T : RealmModel> getFirstData(clazz: Class<T>): T {
        try {
            Realm.getDefaultInstance().use {
                it.beginTransaction()
                var realmData = it.where(clazz).findFirst() as T
                var data = it.copyFromRealm(realmData)
                it.commitTransaction()
                it.close()
                return data
            }
        } catch (e: Exception) {
            Log.e("realm",e.toString())
        }
        return clazz.newInstance()
    }
    //获取所有数据
    fun <T : RealmModel> getDatas(clazz: Class<T>): MutableList<T> {
        try {
            Realm.getDefaultInstance().use {
                it.beginTransaction()
                var realmData = it.where(clazz).findAll()
                var data = it.copyFromRealm(realmData)
                it.commitTransaction()
                it.close()
                return data
            }
        } catch (e: Exception) {
            Log.e("realm",e.toString())
        }
        return mutableListOf()
    }
    //清除数据
    fun <T : RealmModel> clear(clazz: Class<T>){
        try {
            Realm.getDefaultInstance().use {
                it.beginTransaction()
                it.delete(clazz)
                it.commitTransaction()
                it.close()
            }
        } catch (e: Exception) {
            Log.e("realm",e.toString())
        }
    }
}