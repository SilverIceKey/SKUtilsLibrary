package com.silvericekey.skutilslibrary.inline

import android.annotation.SuppressLint
import android.util.Log
import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.net.NetCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

inline fun <T> Observable<T>.execute(callback: NetCallback<T>) {
    execute({
        callback.onSuccess(it)
    }, {
        callback.onError(it)
    })
}

inline fun <T> Observable<T>.execute(noinline onSuccess: (response: T) -> Unit) {
    execute(onSuccess, {
        Log.e("requestError", "${it}")
        ToastUtils.showShort("请求失败")
    })
}

@SuppressLint("CheckResult")
inline fun <T> Observable<T>.execute(noinline onSuccess: (response: T) -> Unit, noinline onError: (throwable: Throwable) -> Unit) {
    subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, {
                onError(it)
            })
}