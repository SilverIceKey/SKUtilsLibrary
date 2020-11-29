package com.silverknife.meizhi.mvp.model

import android.annotation.SuppressLint

class LoginResponse {

    var loginType: Int = 0
    var code: Int = 0
    var isLogin: Boolean = false
    var msg: String? = null
    var userName: String? = null
    var password: String? = null

    var status: Int = 0
    var ret: Int = 0
    var data: LoginResponse? = null
    var info: LoginResponse? = null

    companion object {

        @SuppressLint("CheckResult")
        @JvmStatic
        fun getData(onSuccess: (response: LoginResponse) -> Unit, onError: (throwable: Throwable) -> Unit) {

        }
    }
}
