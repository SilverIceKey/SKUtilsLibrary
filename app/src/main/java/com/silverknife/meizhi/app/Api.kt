package com.silverknife.meizhi.app

import com.silverknife.meizhi.mvp.model.GankResponse
import com.silverknife.meizhi.mvp.model.LoginResponse
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
    fun today():Call<GankResponse>
}
