package com.silvericekey.skutilslibrary

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import com.blankj.utilcode.util.Utils
import com.facebook.drawee.backends.pipeline.Fresco
import com.silvericekey.skutilslibrary.base.BasePresenter
import java.lang.NullPointerException
import java.util.*


/**
 * Created by silverknife on 2017/12/13.
 */

@SuppressLint("StaticFieldLeak")
object SKUtilsLibrary {
    @JvmStatic
    var presenters: ArrayList<BasePresenter> = arrayListOf()
    var context: Context? = null
    get() {
        if (field==null){
            throw NullPointerException("please init SKUtilsLibrary first")
        }
        return field
    }
    private val app_activity: Activity? = null
    /**
     * 框架初始化，获取ApplicationContext
     * */
    fun init(context: Context) {
        this.context = context
        Utils.init(context)
        Fresco.initialize(context)
    }
}
