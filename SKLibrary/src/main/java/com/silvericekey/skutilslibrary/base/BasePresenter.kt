package com.silvericekey.skutilslibrary.base

import com.silvericekey.skutilslibrary.SKUtilsLibrary
import com.silvericekey.skutilslibrary.utils.HttpUtil

abstract class BasePresenter() {
    init {
        SKUtilsLibrary.presenters.add(this)
    }

    open fun onDestroy(){
        HttpUtil.getInstance().clearAllRequest()
    }

    fun refreshData() {

    }
}