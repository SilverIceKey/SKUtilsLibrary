package com.silverknife.meizhi

import com.silvericekey.skutilslibrary.rxjava.InLineCall
import com.silvericekey.skutilslibrary.rxjava.InLineObservable
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
              @Query("password") password: String): InLineCall<LoginResponse>
}
