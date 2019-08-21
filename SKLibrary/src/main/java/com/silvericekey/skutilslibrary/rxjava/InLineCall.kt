package com.silvericekey.skutilslibrary.rxjava

import retrofit2.Call
import retrofit2.Response

inline class InLineCall<T>(val call: Call<T>) {
    fun execute():Response<T> = call.execute()
}