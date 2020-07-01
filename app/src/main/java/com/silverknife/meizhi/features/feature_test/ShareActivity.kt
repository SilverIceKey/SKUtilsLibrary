package com.silverknife.meizhi.features.feature_test

import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R

class ShareActivity : BaseActivity<SharePresenter>() {
    override fun getLayoutID(): Int {
        return R.layout.activity_share
    }

    override fun initView() {

    }

    override fun initPresenter(): SharePresenter {
        return SharePresenter()
    }
}