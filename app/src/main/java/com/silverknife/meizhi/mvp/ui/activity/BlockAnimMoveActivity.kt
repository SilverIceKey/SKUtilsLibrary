package com.silverknife.meizhi.mvp.ui.activity

import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.BlockAnimeMovePresenter
import kotlinx.android.synthetic.main.activity_block_anim_move.*

class BlockAnimMoveActivity : BaseActivity<BlockAnimeMovePresenter>() {
    override fun getLayoutID(): Int {
        return R.layout.activity_block_anim_move
    }

    override fun initView() {
        slide_check.setOnSlideListener({
            ToastUtils.showShort("滑动确认成功")
            slide_check.postDelayed({
                slide_check.getReturn()
            },1000)
        }, {
            ToastUtils.showShort("滑动确认取消")
        })
    }

    override fun initPresenter(): BlockAnimeMovePresenter {
        return BlockAnimeMovePresenter()
    }
}