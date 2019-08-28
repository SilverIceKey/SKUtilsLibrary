package com.silvericekey.skutilslibrary.net

interface NetCallback<T> {
    fun onSuccess(response: T?)
    fun onError(throwable: Throwable)
}