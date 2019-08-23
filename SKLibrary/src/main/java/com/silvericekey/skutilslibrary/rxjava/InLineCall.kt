package com.silvericekey.skutilslibrary.rxjava

import com.silvericekey.skutilslibrary.netUtils.NetCallback
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call

inline class InLineCall<T>(val call: Call<T>) {
    fun execute(callback: NetCallback<T>) {
        Observable.create(object : ObservableOnSubscribe<T> {
            override fun subscribe(emitter: ObservableEmitter<T>) {
                var response = call.execute()
                if (response.isSuccessful){
                    response.body()?.let { emitter.onNext(it) }
                }else{
                    Throwable(response.message())?.let { emitter.onError(it) }
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    callback.onSuccess(it)
                }, {
                    callback.onError(it)
                })
    }
}