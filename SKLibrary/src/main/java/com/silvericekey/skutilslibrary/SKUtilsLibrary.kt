package com.silvericekey.skutilslibrary

import android.annotation.SuppressLint
import android.content.Context
import com.blankj.utilcode.util.Utils
import com.facebook.drawee.backends.pipeline.Fresco


/**
 * Created by silverknife on 2017/12/13.
 */

@SuppressLint("StaticFieldLeak")
object SKUtilsLibrary {

    /**
     * 获取Context如果没有初始化会抛出异常
     */
    @JvmStatic
    var context: Context? = null
        get() {
            if (field == null) {
                throw NullPointerException("please init SKUtilsLibrary first")
            }
            return field
        }

    /**
     * 框架初始化，获取ApplicationContext
     * */
    fun init(context: Context) {
        this.context = context
        Utils.init(context)
        Fresco.initialize(context)
    }
}
