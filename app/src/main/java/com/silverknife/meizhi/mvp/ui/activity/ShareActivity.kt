package com.silverknife.meizhi.mvp.ui.activity

import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.SharePresenter

class ShareActivity : BaseActivity<SharePresenter>() {
    override fun getLayoutID(): Int {
        return R.layout.activity_share
    }

    override fun initView() {

    }

    override fun initPresenter(): SharePresenter {
        return  SharePresenter()
    }
}