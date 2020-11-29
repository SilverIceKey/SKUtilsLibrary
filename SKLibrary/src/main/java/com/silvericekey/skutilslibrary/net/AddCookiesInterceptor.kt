package com.silvericekey.skutilslibrary.net

import android.content.Context
import com.silvericekey.skutilslibrary.base.BaseApplication
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AddCookiesInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var builder = chain.request().newBuilder()
        var cookies = BaseApplication.getApp().getSharedPreferences("cookie",Context.MODE_PRIVATE).getStringSet("cookies",null)
        if (cookies != null) {
            addCookies(builder,cookies)
        }
        return chain.proceed(builder.build());
    }

    fun addCookies(builder:Request.Builder,cookies:Set<String>){
        for (cookie in cookies) {
            builder.addHeader("Cookie", cookie);
        }
    }
}