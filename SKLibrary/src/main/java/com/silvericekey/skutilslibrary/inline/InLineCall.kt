package com.silvericekey.skutilslibrary.inline

import android.annotation.SuppressLint
import com.silvericekey.skutilslibrary.net.NetCallback
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call

inline fun <T> Call<T>.execute(callback: NetCallback<T>) {
    execute({
        callback.onSuccess(it)
    }, {
        callback.onError(it)
    })
}

@SuppressLint("CheckResult")
inline fun <T> Call<T>.execute(noinline onSuccess: (response: T) -> Unit, noinline onError: (throwable: Throwable) -> Unit) {
    Observable.create(object : ObservableOnSubscribe<T> {
        override fun subscribe(emitter: ObservableEmitter<T>) {
            val response = execute()
            if (response.isSuccessful) {
                response.body()?.let { emitter.onNext(it) }
            } else {
                Throwable(response.message()).let { emitter.onError(it) }
            }
        }
    })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, {
                onError(it)
            })
}