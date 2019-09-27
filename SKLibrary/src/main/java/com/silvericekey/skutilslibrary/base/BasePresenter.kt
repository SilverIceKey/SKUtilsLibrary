package com.silvericekey.skutilslibrary.base

import com.silvericekey.skutilslibrary.SKUtilsLibrary

abstract class BasePresenter {
    constructor() {
        SKUtilsLibrary.presenters.add(this)
    }

    abstract fun onDestroy()

    fun refreshData() {

    }
}