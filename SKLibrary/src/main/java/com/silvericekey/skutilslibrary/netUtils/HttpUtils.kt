package com.silvericekey.skutilslibrary.netUtils

import android.text.TextUtils
import android.util.Log
import okhttp3.Cookie
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class HttpUtils {
    private var okHttpClient: OkHttpClient
    private var retrofit: Retrofit

    companion object {
        private var httpUtils: HttpUtils? = null
        @JvmStatic
        fun getInstance(): HttpUtils {
            if (httpUtils == null) {
                throw Exception("Please init in Application or other place before use")
            }
            return httpUtils!!
        }

        @JvmStatic
        fun init(baseUrl: String) {
            if (httpUtils == null) {
                synchronized(HttpUtils::class) {
                    if (httpUtils == null) {
                        httpUtils = HttpUtils(baseUrl)
                    }
                }
            }
        }
    }

    constructor(baseUrl: String) {
        if (TextUtils.isEmpty(baseUrl)) {
            throw Exception("Please set base url first")
        }
        val loggingInterceptor = HttpLoggingInterceptor(object:HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.i("RetrofitLog", "retrofitBack = $message")
            }
        })
        val receivedCookiesInterceptor = ReceivedCookiesInterceptor()
        val addCookiesInterceptor = AddCookiesInterceptor()
        loggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(addCookiesInterceptor)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build()
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    fun addInterceptor(interceptor: Interceptor):HttpUtils{
        okHttpClient = okHttpClient.newBuilder().addInterceptor(interceptor).build()
        retrofit = retrofit.newBuilder().client(okHttpClient).build()
        return this
    }

    fun changeUrl(baseUrl: String) :HttpUtils{
        retrofit = retrofit.newBuilder().baseUrl(baseUrl).client(okHttpClient).build()
        return this
    }

    fun <T> obtainClass(clz: Class<T>): T {
        return retrofit.create(clz)
    }
}