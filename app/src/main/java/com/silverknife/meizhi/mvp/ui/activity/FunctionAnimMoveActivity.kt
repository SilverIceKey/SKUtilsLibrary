package com.silverknife.meizhi.mvp.ui.activity

import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.FunctionAnimMovePresenter

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