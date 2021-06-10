package com.silverknife.meizhi.common;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/")
    Call<String> getData();
}
