package com.silvericekey.skutilslibrary.netUtils

interface NetCallback<T> {
    abstract fun onSuccess(response: T?)
}