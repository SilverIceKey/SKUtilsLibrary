package com.silverknife.meizhi.app.net

import okhttp3.Interceptor
import okhttp3.Response

class TestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request();
        println("拦截测试")
        var response = chain.proceed(request)
        return response
    }
}