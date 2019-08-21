package com.silvericekey.skutilslibrary.uiUtils

import android.animation.ObjectAnimator
import android.widget.Toast

import com.silvericekey.skutilslibrary.SKUtilsLibrary

/**
 * Created by silverknife on 2017/12/13.
 */

object SKToastUtils {
    fun showToast(msg: String) {
        Toast.makeText(SKUtilsLibrary.getContext(), msg, Toast.LENGTH_SHORT).show()
    }
}
