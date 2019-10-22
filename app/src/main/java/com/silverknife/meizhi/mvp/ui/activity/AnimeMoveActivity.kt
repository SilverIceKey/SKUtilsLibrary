package com.silverknife.meizhi.mvp.ui.activity

import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.AnimeMovePresenter

class AnimeMoveActivity : BaseActivity<AnimeMovePresenter>() {
    override fun getLayoutID(): Int {
        return R.layout.activity_anime_move
    }

    override fun initView() {

    }

    override fun initPresenter(): AnimeMovePresenter {
        return AnimeMovePresenter()
    }
}