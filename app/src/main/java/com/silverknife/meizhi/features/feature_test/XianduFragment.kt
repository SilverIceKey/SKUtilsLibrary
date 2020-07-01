package com.silverknife.meizhi.features.feature_test

import android.view.View
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silverknife.meizhi.R

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