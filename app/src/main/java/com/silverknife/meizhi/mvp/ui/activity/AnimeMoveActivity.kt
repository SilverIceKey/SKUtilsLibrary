package com.silverknife.meizhi.mvp.ui.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.MotionEvent
import android.widget.LinearLayout
import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.AnimeMovePresenter
import kotlinx.android.synthetic.main.activity_anime_move.*

class AnimeMoveActivity : BaseActivity<AnimeMovePresenter>() {
    override fun getLayoutID(): Int {
        return R.layout.activity_anime_move
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

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val y = event!!.getRawY().toInt()
        val x = event!!.getRawX().toInt()
        //保存上一个滑动事件手指的坐标
        var mLastY: Int = 0
        var mLastX: Int = 0
        var mLastTime:Long = 0
        //刚触摸时手指的坐标
        var mDownTime:Long = 0
        var mDownY: Int = 0
        var mDownX: Int = 0
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = event.x.toInt()
                mDownY = event.y.toInt()
                mDownTime = System.currentTimeMillis()
            }
            MotionEvent.ACTION_UP -> {
                mLastTime = System.currentTimeMillis()
            }
        }
        mLastY = y
        mLastX = x
        return false
    }

    override fun initPresenter(): AnimeMovePresenter {
        return AnimeMovePresenter()
    }
}