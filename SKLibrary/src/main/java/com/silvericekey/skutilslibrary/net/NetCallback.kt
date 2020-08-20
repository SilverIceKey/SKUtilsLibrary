package com.silvericekey.skutilslibrary.net

/**
 * 请求回调
 */
interface NetCallback<T> {
    /**
     * 请求成功
     */
    fun onSuccess(response: T?)
    fun onError(throwable: Throwable)
}