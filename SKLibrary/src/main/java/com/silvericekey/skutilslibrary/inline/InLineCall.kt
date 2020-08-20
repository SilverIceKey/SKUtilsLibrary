package com.silvericekey.skutilslibrary.inline

import android.annotation.SuppressLint
import android.util.Log
import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.net.NetCallback
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call

/**
 * Call内联函数
 */
inline fun <T> Call<T>.execute(callback: NetCallback<T>) {
    execute({
        callback.onSuccess(it)
    }, {
        callback.onError(it)
    })
}
/**
 * Call内联函数
 */
inline fun <T> Call<T>.execute(noinline onSuccess: (response: T) -> Unit) {
    execute(onSuccess,{
        Log.e("requestError", "${it}")
        ToastUtils.showShort("请求失败")
    })
}
/**
 * Call内联函数
 */
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