package com.silverknife.meizhi.features.feature_news

import com.silvericekey.skutilslibrary.base.BasePresenter

/**
 * <pre>
 *     time   : 2020/07/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class NewsListPresenter : BasePresenter() {
    override fun onDestroy() {

    }

    fun getData(type: String, onSuccess: (MutableList<NewsModel>) -> Unit,onError: (Throwable) -> Unit) {
        NewsResponse.getNews(type, onSuccess,onError)
    }
}