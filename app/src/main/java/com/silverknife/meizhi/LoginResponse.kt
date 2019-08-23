package com.silverknife.meizhi

import android.annotation.SuppressLint
import com.silvericekey.skutilslibrary.netUtils.HttpUtil
import com.silvericekey.skutilslibrary.netUtils.NetCallback

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
        fun getData(callback: NetCallback<LoginResponse>) {
            HttpUtil.getInstance()
                    .addInterceptor(TestInterceptor())
                    .obtainClass(Api::class.java)
                    .login("18368402184", "400938")
                    .execute(callback)
//                    .setSchedulers(callback)
        }
    }
}
