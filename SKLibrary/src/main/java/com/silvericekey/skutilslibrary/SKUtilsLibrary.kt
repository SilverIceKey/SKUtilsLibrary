package com.silvericekey.skutilslibrary

import android.annotation.SuppressLint
import android.content.Context
import android.util.DisplayMetrics

import com.facebook.drawee.backends.pipeline.Fresco

import me.jessyan.autosize.AutoSize
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits
import me.jessyan.autosize.unit.UnitsManager
import java.lang.NullPointerException

/**
 * Created by silverknife on 2017/12/13.
 */

@SuppressLint("StaticFieldLeak")
object SKUtilsLibrary {
    var context: Context?=null
    fun init(context: Context) {
        this.context = context
        Fresco.initialize(context)
    }
}
