package com.silverknife.meizhi.mvp.ui.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.LinearLayout

class SlideCheckView : LinearLayout {
    var block: View? = null
    var blockWrapper: BlockWrapper? = null
    lateinit var onConfirm: () -> Unit
    lateinit var onCancel: () -> Unit

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setOnSlideListener(onConfirm: () -> Unit, onCancel: () -> Unit) {
        this.onConfirm = onConfirm
        this.onCancel = onCancel
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        block = getChildAt(0)

        blockWrapper = BlockWrapper()
        block!!.setOnTouchListener(blockTouchListener())
    }

    inner class blockTouchListener : View.OnTouchListener {
        //保存上一个滑动事件手指的坐标
        private var mLastY: Int = 0
        private var mLastX: Int = 0
        //刚触摸时手指的坐标
        private var mDownY: Int = 0
        private var mDownX: Int = 0

        private var dy: Int = 0//和上一次滑动的差值 设置为全局变量是因为 UP里也要使用

        private var isClick: Boolean = false
        private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop
        @SuppressLint("ObjectAnimatorBinding")
        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            val y = event!!.getRawY().toInt()
            val x = event!!.getRawX().toInt()
            when (event!!.action) {
                MotionEvent.ACTION_DOWN -> {
                    mDownX = event.x.toInt()
                    mDownY = event.y.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    var lp = block!!.layoutParams as LinearLayout.LayoutParams
                    lp.leftMargin += (event.rawX - mLastX).toInt()
                    if (lp.leftMargin <= 0) {
                        lp.leftMargin = 0
                    } else if (lp.leftMargin >= (this@SlideCheckView.width - this@SlideCheckView.paddingRight - block!!.width)) {
                        lp.leftMargin = this@SlideCheckView.width - this@SlideCheckView.paddingRight - block!!.width
                    }
                    block!!.layoutParams = lp
                }
                MotionEvent.ACTION_CANCEL -> {

                }
                MotionEvent.ACTION_UP -> {
                    if (blockWrapper!!.getMarginLeft() < this@SlideCheckView.width - this@SlideCheckView.paddingRight - blockWrapper!!.getWidth()) {
                        val set = AnimatorSet()
                        set.playTogether(
                                ObjectAnimator.ofInt(blockWrapper, "marginLeft", blockWrapper!!.getMarginLeft(), 0)
                        )
                        set.setDuration(200).start()
                        onCancel()
                    } else {
                        onConfirm()
                    }
                }
            }
            mLastY = y
            mLastX = x
            return true
        }
    }

    fun getReturn() {
        val set = AnimatorSet()
        set.playTogether(
                ObjectAnimator.ofInt(blockWrapper, "marginLeft", blockWrapper!!.getMarginLeft(), 0)
        )
        set.setDuration(200).start()
    }

    inner class BlockWrapper {
        var params: LinearLayout.LayoutParams

        constructor() {
            params = block!!.layoutParams as LayoutParams
        }

        fun getWidth(): Int {
            return params.width
        }

        fun getHeight(): Int {
            return params.height
        }

        fun getMarginTop(): Int {
            return params.topMargin
        }

        fun getMarginLeft(): Int {
            return params.leftMargin
        }

        fun setWidth(width: Int) {
            params.width = width
            block!!.layoutParams = params
        }

        fun setHeight(height: Int) {
            params.height = height
            block!!.layoutParams = params
        }

        fun setMarginTop(marginTop: Int) {
            params.topMargin = marginTop
            block!!.layoutParams = params
        }

        fun setMarginLeft(marginLeft: Int) {
            params.leftMargin = marginLeft
            block!!.layoutParams = params
        }
    }

    fun setBlockLayoutParams(leftMargin: Int, topMargin: Int, width: Int, height: Int) {
        var lp = block!!.layoutParams as LinearLayout.LayoutParams
        lp.leftMargin = leftMargin
        lp.topMargin = topMargin
        lp.width = width
        lp.height = height
        block!!.layoutParams = lp
    }
}