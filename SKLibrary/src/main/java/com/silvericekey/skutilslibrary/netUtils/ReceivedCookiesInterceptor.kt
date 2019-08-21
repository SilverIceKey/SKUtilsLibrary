package com.silvericekey.skutilslibrary.netUtils

import android.content.Context
import com.silvericekey.skutilslibrary.SKUtilsLibrary
import com.silvericekey.skutilslibrary.data.Preference
import okhttp3.Interceptor
import okhttp3.Response

class ReceivedCookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var originResponse = chain.proceed(request)
        if (!originResponse.headers("Set-Cookie").isEmpty()){
            var cookies = HashSet<String>()
            for (header in originResponse.headers("Set-Cookie")){
                cookies.add(header)
            }
            var sharedPreferences = SKUtilsLibrary.getContext().getSharedPreferences("cookie",Context.MODE_PRIVATE)
            var editor = sharedPreferences.edit()
            editor.putStringSet("cookies",cookies)
            editor.apply()

        }
        return originResponse
    }
}
