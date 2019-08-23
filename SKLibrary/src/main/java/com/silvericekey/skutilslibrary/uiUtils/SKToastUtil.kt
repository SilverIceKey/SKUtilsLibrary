package com.silvericekey.skutilslibrary.uiUtils

import android.view.Gravity
import android.widget.Toast
import com.silvericekey.skutilslibrary.SKUtilsLibrary

/**
 * Created by silverknife on 2017/12/13.
 */

object SKToastUtil {
    var gravity = Gravity.BOTTOM
    var xOffset = 0
    var yOffset = 0
    fun showToast(msg: String) {
        var toast = Toast.makeText(SKUtilsLibrary.context!!, msg, Toast.LENGTH_SHORT)
        toast.setGravity(gravity, xOffset, yOffset)
        toast.show()
    }
    fun showToast(msg: String,gravity: Int) {
        var toast = Toast.makeText(SKUtilsLibrary.context!!, msg, Toast.LENGTH_SHORT)
        toast.setGravity(gravity, xOffset, yOffset)
        toast.show()
    }

    fun showToast(msg: String,gravity: Int,xOffset:Int,yOffset:Int) {
        var toast = Toast.makeText(SKUtilsLibrary.context!!, msg, Toast.LENGTH_SHORT)
        toast.setGravity(gravity, xOffset, yOffset)
        toast.show()
    }
}
