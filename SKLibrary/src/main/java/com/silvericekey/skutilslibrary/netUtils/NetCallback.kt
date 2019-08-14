package com.silvericekey.skutilslibrary.netUtils

interface NetCallback<T> {
    fun onSuccess(response: T?)
}