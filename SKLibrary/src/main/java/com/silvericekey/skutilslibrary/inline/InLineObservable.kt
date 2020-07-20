package com.silvericekey.skutilslibrary.inline

import android.annotation.SuppressLint
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