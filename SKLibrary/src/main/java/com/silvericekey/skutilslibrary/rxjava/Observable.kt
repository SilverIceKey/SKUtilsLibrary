package com.silvericekey.skutilslibrary.rxjava

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

inline class Observable<T>(val observable: Observable<T>){
    fun setSchedulers():Observable<T> = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

}