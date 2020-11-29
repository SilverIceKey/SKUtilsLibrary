package com.silvericekey.skutilslibrary.base

abstract class BasePresenter {
    constructor() {
        SKUtilsLibrary.presenters.add(this)
    }

    abstract fun onDestroy()

    fun refreshData() {

    }
}