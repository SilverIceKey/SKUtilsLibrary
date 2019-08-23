package com.silvericekey.skutilslibrary.netUtils

import android.content.Context
import com.silvericekey.skutilslibrary.SKUtilsLibrary
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AddCookiesInterceptor :Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var builder = chain.request().newBuilder()
        var cookies = SKUtilsLibrary.context!!.getSharedPreferences("cookie",Context.MODE_PRIVATE).getStringSet("cookies",null)
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