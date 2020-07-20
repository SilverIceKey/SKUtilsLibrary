package com.silverknife.meizhi.features.feature_news

import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.inline.execute
import com.silvericekey.skutilslibrary.utils.HttpUtil
import com.silverknife.meizhi.app.net.Api

/**
 * <pre>
 *     time   : 2020/07/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class NewsResponse {
    var reason = ""
    var error_code = 0
    var result: NewsResponse? = null
    var stat = 0
    var data = mutableListOf<NewsModel>()

    companion object {
        @JvmStatic
        fun getNews(type: String, onSuccess: (MutableList<NewsModel>) -> Unit, onError: (Throwable) -> Unit) {
            HttpUtil.getInstance()
                    .changeUrl("http://v.juhe.cn/toutiao/")
                    .obtainClass(Api::class.java)
                    .getNews(type)
                    .execute({
                        onSuccess(it.result?.data!!)
                    }, {
                        onError(it)
                        ToastUtils.showShort("请求异常:${it}")
                    })
        }
    }
}