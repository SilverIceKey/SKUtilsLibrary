package com.silverknife.meizhi.features.feature_gank

import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import kotlinx.android.synthetic.main.activity_gank_detail.*

class GankDetailActivity : BaseActivity<GankDetailPresenter>() {
    override fun getLayoutID(): Int {
        return R.layout.activity_gank_detail
    }

    override fun initView() {
        back.setOnClickListener { finish() }
        content_title.text = intent.getStringExtra("title")
        webview.loadUrl(intent.getStringExtra("url"))
    }

    override fun initPresenter(): GankDetailPresenter {
        return GankDetailPresenter(this)
    }
}