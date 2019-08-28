package com.silvericekey.skutilslibrary

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.util.Util
import com.facebook.drawee.backends.pipeline.Fresco


/**
 * Created by silverknife on 2017/12/13.
 */

@SuppressLint("StaticFieldLeak")
object SKUtilsLibrary {
    var context: Context?=null
    private val app_activity: Activity? = null
    fun init(context: Context) {
        this.context = context
        Utils.init(context)
        Fresco.initialize(context)
    }
}
