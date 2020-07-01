package com.silverknife.meizhi.app.net

import com.silverknife.meizhi.features.feature_gank.GankResponse
import com.silverknife.meizhi.features.feature_news.NewsResponse
import com.silverknife.meizhi.features.feature_test.LoginResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    /**
     *
     * @param phone
     * @param password
     * @return
     */
    @GET("api.php?s=Login/index")
    fun login(@Query("account") phoneu: String,
              @Query("password") password: String): Call<LoginResponse>

    @GET("api/today")
    fun today(): Call<GankResponse>

    /**
     * @param type 新闻类型
     */
    @GET("index?key=ed4145284d5a3f4017c57b5f70e37557")
    fun getNews(@Query("type") type: String):Call<NewsResponse>
}
