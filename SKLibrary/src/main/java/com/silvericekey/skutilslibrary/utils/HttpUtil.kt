package com.silvericekey.skutilslibrary.utils

import android.text.TextUtils
import android.util.Log
import com.silvericekey.skutilslibrary.SKUtilsLibrary
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

class HttpUtil {
    private var okHttpClient: OkHttpClient
    private var okHttpWebSocketClient: OkHttpClient
    private var retrofit: Retrofit

    companion object {
        private var httpUtils: HttpUtil? = null
        @JvmStatic
        fun getInstance(): HttpUtil {
            if (httpUtils == null) {
                throw Exception("Please init in Application or other place before use")
            }
            return httpUtils!!
        }

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

        @JvmStatic
        fun webSocketTest() {
            var wsUrl = "wss://echo.websocket.org/"
            var request = Request.Builder().url(wsUrl).build()
            var okHttpClient = OkHttpClient.Builder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    .build()
            okHttpClient.newWebSocket(request, object : WebSocketListener() {
                var mWebSocket: WebSocket? = null
                override fun onOpen(webSocket: WebSocket, response: Response) {
                    super.onOpen(webSocket, response)
                    mWebSocket = webSocket
                    println("client onOpen")
                    println("client request header:" + response.request.headers)
                    println("client response header:" + response.headers)
                    println("client response:" + response)
                    //开启消息定时发送
                    startTask()
                }

                var msgCount = 0
                private fun startTask() {
                    var mTimer = Timer()
                    val timerTask = object : TimerTask() {
                        override fun run() {
                            if (mWebSocket == null) return
                            msgCount++
                            if (msgCount == 15) {
                                mWebSocket!!.close(1000, "11")
                                cancel()
                                return
                            }
                            var sendStr = "发送-" + msgCount
                            println(sendStr)
                            mWebSocket!!.send(msgCount.toString())
                            //除了文本内容外，还可以将如图像，声音，视频等内容转为ByteString发送
                            //boolean send(ByteString bytes);
                        }
                    }
                    mTimer.schedule(timerTask, 0, 1000)
                }

                override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                    super.onMessage(webSocket, bytes)
                }

                override fun onMessage(webSocket: WebSocket, text: String) {
                    super.onMessage(webSocket, text)
                    println("接收:" + text)
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    super.onClosing(webSocket, code, reason)
                    println("client onClosing")
                    println("code:" + code + " reason:" + reason)
                }

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    super.onClosed(webSocket, code, reason)
                    println("client onClosed")
                    println("code:" + code + " reason:" + reason)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    super.onFailure(webSocket, t, response)
                    //出现异常会进入此回调
                    println("client onFailure")
                    println("throwable:" + t)
                    println("response:" + response)
                }
            }).request()
        }
    }

    constructor(baseUrl: String) {
        if (TextUtils.isEmpty(baseUrl)) {
            throw Exception("Please set base url first")
        }
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.i("RetrofitLog", "retrofitBack = $message")
            }
        })
        val receivedCookiesInterceptor = ReceivedCookiesInterceptor()
        val addCookiesInterceptor = AddCookiesInterceptor()
        //缓存文件夹
        val cacheFile = File(SKUtilsLibrary.context!!.getExternalCacheDir().toString(), "cache")
        if (!cacheFile.exists()){
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

    fun addInterceptor(interceptor: Interceptor): HttpUtil {
        okHttpClient = okHttpClient.newBuilder().addInterceptor(interceptor).build()
        retrofit = retrofit.newBuilder().client(okHttpClient).build()
        return this
    }

    fun changeUrl(baseUrl: String): HttpUtil {
        retrofit = retrofit.newBuilder().baseUrl(baseUrl).client(okHttpClient).build()
        return this
    }

    fun webSocket(url: String, listener: WebSocketListener) {
        var request = Request.Builder().url(url).build()
        okHttpWebSocketClient.newWebSocket(request, listener)
    }

    fun <T> obtainClass(clz: Class<T>): T {
        return retrofit.create(clz)
    }
}