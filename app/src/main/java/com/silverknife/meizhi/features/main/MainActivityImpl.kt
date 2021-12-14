package com.silverknife.meizhi.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import com.silvericekey.skutilslibrary.net.RetrofitClient
import com.silvericekey.skutilslibrary.utils.log.LoggerHelper
import com.silvericekey.skutilslibrary.utils.thread.ThreadUtil
import com.silverknife.meizhi.R
import com.silverknife.meizhi.common.Api
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityImpl : AppCompatActivity(){
    var txtStr = "111"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt.text = txtStr
        RetrofitClient
                .getInstance()
                .createService(Api::class.java)
                .data
                .enqueue(object :Callback<String>{
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        txt.text = response.body()!!
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        txt.text = t.toString()
                    }

                })
    }
}