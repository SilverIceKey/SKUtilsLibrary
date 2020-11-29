package com.silverknife.meizhi.features.feature_news

import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import kotlinx.android.synthetic.main.activity_news_detail.*

/**
 * <pre>
 *     time   : 2020/07/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class NewsDetailActivity : BaseActivity<NewsDetailPresenter>() {
    override fun getLayoutID(): Int {
        return R.layout.activity_news_detail
    }

    override fun initView() {
        var url = intent.getStringExtra("url")
        var title = intent.getStringExtra("title")
        back.setOnClickListener { finish() }
        content_title.text = title
        webview.loadUrl(url)
    }

    override fun initPresenter(): NewsDetailPresenter {
        return NewsDetailPresenter()
    }
}