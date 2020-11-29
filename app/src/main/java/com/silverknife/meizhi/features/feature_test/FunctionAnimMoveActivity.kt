package com.silverknife.meizhi.features.feature_test

import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R

class FunctionAnimMoveActivity: BaseActivity<FunctionAnimMovePresenter>() {
    override fun getLayoutID(): Int {
        return R.layout.activity_func_anim_move
    }

    override fun initView() {
    }

    override fun initPresenter(): FunctionAnimMovePresenter {
        return FunctionAnimMovePresenter()
    }
}