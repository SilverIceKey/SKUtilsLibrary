package com.silverknife.meizhi.mvp.ui.activity

import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.ViewDragHelpPresenter

class ViewDragHelpActivity:BaseActivity<ViewDragHelpPresenter>() {
    override fun getLayoutID(): Int {
         return R.layout.activity_viewdraghelp
    }
    override fun initView() {

    }

    override fun initPresenter(): ViewDragHelpPresenter {
        return ViewDragHelpPresenter()
    }
}