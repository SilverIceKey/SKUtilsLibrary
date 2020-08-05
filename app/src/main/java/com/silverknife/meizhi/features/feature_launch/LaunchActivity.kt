package com.silverknife.meizhi.features.feature_launch

import android.content.Intent
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.features.feature_main.MainActivity

class LaunchActivity:BaseActivity<LaunchPresenter>() {
    override fun getLayoutID(): Int {
        return R.layout.activity_launch
    }
    override fun initView() {
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun initPresenter(): LaunchPresenter {
        return LaunchPresenter()
    }
}