package com.silvericekey.skutilslibrary.rxjava

import com.silvericekey.skutilslibrary.net.NetCallback
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

inline class InLineObservable<T>(val observable: Observable<T>) {
    fun setSchedulers(callback: NetCallback<T>) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onSuccess(it)
                }, {
                    callback.onError(it)
                })
    }
}