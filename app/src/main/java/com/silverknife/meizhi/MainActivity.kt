package com.silverknife.meizhi

import android.view.View
import com.silvericekey.skutilslibrary.base.BaseActivity

class MainActivity : BaseActivity<MainPresenter>(),IMainView {
    override fun getLayoutID(): Int = R.layout.activity_main
    override fun initView() {

    }

    override fun initPresenter(): MainPresenter = MainPresenter(this@MainActivity)

    fun request(view: View) {
        mPresenter.getData()
    }

}
