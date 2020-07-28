package com.silvericekey.skutilslibrary.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.TextUtils
import android.util.Log
import com.silvericekey.skutilslibrary.SKUtilsLibrary
import com.silvericekey.skutilslibrary.interpolator.CustomCacheInterceptor
import com.silvericekey.skutilslibrary.net.AddCookiesInterceptor
import com.silvericekey.skutilslibrary.net.ReceivedCookiesInterceptor
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import okio.ByteString
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * 网络请求工具类
 * 单例模式
 * */
class HttpUtil {
    private var okHttpClient: OkHttpClient
    private var okHttpWebSocketClient: OkHttpClient
    private var retrofit: Retrofit

    companion object {
        private val TAG = "HttpUtil"
        private var httpUtils: HttpUtil? = null
        @JvmStatic
        fun getInstance(): HttpUtil {
            if (httpUtils == null) {
                throw Exception("Please init in Application or other place before use")
            }
            return httpUtils!!
        }

        /**
         * 是否有网络检测
         * */
        fun hasNetwork(): Boolean? {
            var isConnected: Boolean? = false // Initial Value
            val connectivityManager = SKUtilsLibrary.context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected)
                isConnected = true
            return isConnected
        }

        /**
         * 初始化连接host
         * @param baseUrl 域名连接
         * */
        @JvmStatic
        fun init(baseUrl: String) {
            if (httpUtils == null) {
                synchronized(HttpUtil::class) {
                    if (httpUtils == null) {
                        httpUtils = HttpUtil(baseUrl)
                    }
                }
            }
        }
    }

    /**
     * 初始化网络
     * */
    constructor(baseUrl: String) {
        if (TextUtils.isEmpty(baseUrl)) {
            throw Exception("Please set base url first")
        }
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("RetrofitLog", "retrofitBack = $message")
            }
        })
        val receivedCookiesInterceptor = ReceivedCookiesInterceptor()
        val addCookiesInterceptor = AddCookiesInterceptor()
        //缓存文件夹
        val cacheFile = File(SKUtilsLibrary.context!!.externalCacheDir, "")
        if (!cacheFile.exists()) {
            cacheFile.mkdirs()
        }
        //缓存大小为10M
        val cacheSize: Long = 10 * 1024 * 1024
        //创建缓存对象
        val cache = Cache(cacheFile, cacheSize)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpWebSocketClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(addCookiesInterceptor)
                .addInterceptor(receivedCookiesInterceptor)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .writeTimeout(10000L, TimeUnit.MILLISECONDS)
                .pingInterval(40, TimeUnit.SECONDS)
                .build()
        okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(addCookiesInterceptor)
                .addInterceptor(receivedCookiesInterceptor)
                .addInterceptor(CustomCacheInterceptor())
                .cache(cache)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .writeTimeout(10000L, TimeUnit.MILLISECONDS)
                .build()
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    /**
     * 添加拦截器
     * */
    fun addInterceptor(interceptor: Interceptor): HttpUtil {
        okHttpClient = okHttpClient.newBuilder().addInterceptor(interceptor).build()
        retrofit = retrofit.newBuilder().client(okHttpClient).build()
        return this
    }

    /**
     * 修改基础连接host
     * */
    fun changeUrl(baseUrl: String): HttpUtil {
        retrofit = retrofit.newBuilder().baseUrl(baseUrl).client(okHttpClient).build()
        return this
    }

    /**
     * 创建websocket
     * */
    fun webSocket(url: String, listener: WebSocketListener) {
        var request = Request.Builder().url(url).build()
        okHttpWebSocketClient.newWebSocket(request, listener)
    }

    /**
     * api类的转换
     * */
    fun <T> obtainClass(clz: Class<T>): T {
        return retrofit.create(clz)
    }
}