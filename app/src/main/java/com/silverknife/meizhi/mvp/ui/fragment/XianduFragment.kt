package com.silverknife.meizhi.mvp.ui.fragment

import android.view.View
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.XianduPresenter

class XianduFragment: BaseFragment<XianduPresenter>() {
    override fun getLayoutID(): Int {
        return R.layout.fragment_xiandu
    }

    override fun initView(view: View) {

    }

    override fun initPresenter(): XianduPresenter {
        return XianduPresenter()
    }
}