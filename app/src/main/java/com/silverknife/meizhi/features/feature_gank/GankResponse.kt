package com.silverknife.meizhi.features.feature_gank

import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.inline.execute
import com.silvericekey.skutilslibrary.utils.HttpUtil
import com.silverknife.meizhi.app.net.Api

class GankResponse {
    var category: ArrayList<String>? = null
    var error: Boolean = false
    var results: Map<String, ArrayList<GankItemModel>>? = null

    companion object {
        @JvmStatic
        fun getToday(onSuccess: (response: GankResponse) -> Unit) {
            HttpUtil.getInstance()
                    .changeUrl("http://gank.io/")
                    .obtainClass(Api::class.java)
                    .today()
                    .execute({
                        onSuccess(it)
                    }, {
                        ToastUtils.showShort("请求失败,网络异常")
                    })
        }
    }
}