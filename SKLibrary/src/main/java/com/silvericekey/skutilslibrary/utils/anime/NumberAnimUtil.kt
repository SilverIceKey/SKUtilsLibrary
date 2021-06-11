package com.silvericekey.skutilslibrary.utils.anime

import android.animation.*
import android.annotation.SuppressLint
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.regex.Pattern

class NumberAnimUtil {
    /**
     * 起始值 默认 0
     */
    private var mNumStart = "0"
    /**
     * 结束值
     */
    private var mNumEnd: String = "0"
    /**
     * 动画总时间 默认 2000 毫秒
     */
    private var mDuration: Long = 2000
    /**
     * 前缀
     */
    private var mPrefixString = ""
    /**
     * 后缀
     */
    private var mPostfixString = ""
    /**
     * 是否是整数
     */
    private var isInt = false
    /**
     * 动画更新监听
     */
    private var animatorUpdaterListeners = ArrayList<ValueAnimator.AnimatorUpdateListener>()
    private var animatorListeners = ArrayList<AnimatorListenerAdapter>()
    /**
     * 插值器
     */
    private var interpolator:TimeInterpolator = AccelerateDecelerateInterpolator()
    private lateinit var animator: ValueAnimator
    fun setStart(start: String): NumberAnimUtil {
        val p = Pattern.compile("[0-9]*\\.?[0-9]+")
        val m = p.matcher(start)
        if (m.matches()) {
            mNumStart = start
            if (mNumStart.contains(".")) {
                isInt = true
            } else {
                isInt = false
            }
        } else {
            mNumStart = "0"
        }
        return this
    }

    fun setEnd(end: String): NumberAnimUtil {
        val p = Pattern.compile("[0-9]*\\.?[0-9]+")
        val m = p.matcher(end)
        if (m.matches()) {
            mNumEnd = end
            if (mNumEnd.contains(".")) {
                isInt = true
            } else {
                isInt = false
            }
        } else {
            mNumEnd = "0"
        }
        mNumEnd = end
        return this
    }

    fun setDuration(duration: Long): NumberAnimUtil {
        mDuration = duration
        return this
    }

    fun setPrefixString(prefixString: String): NumberAnimUtil {
        mPrefixString = prefixString
        return this
    }

    fun setPostfixString(postfixString: String): NumberAnimUtil {
        mPostfixString = postfixString
        return this
    }

    fun setIsInt(isInt: Boolean): NumberAnimUtil {
        this.isInt = isInt
        return this
    }

    fun setInterpolator(interpolator: TimeInterpolator): NumberAnimUtil {
        this.interpolator = interpolator
        return this
    }

    fun addUpdateListener(listener: ValueAnimator.AnimatorUpdateListener): NumberAnimUtil {
        animatorUpdaterListeners.add(listener)
        return this
    }

    fun addListener(listener: AnimatorListenerAdapter): NumberAnimUtil {
        animatorListeners.add(listener)
        return this
    }

    fun pause() {
        if (animator.isRunning) {
            animator.pause()
        }
    }

    fun resume() {
        if (animator.isPaused) {
            animator.resume()
        }
    }

    fun isRunning():Boolean{
        return animator.isRunning
    }

    fun stop() {
        animator.cancel()
    }

    @SuppressLint("SetTextI18n")
    fun playOn(textView: TextView) {
        animator = ValueAnimator.ofObject(BigDecimalEvaluator(), BigDecimal(mNumStart), BigDecimal(mNumEnd))
        animator.setDuration(mDuration)
        animator.setInterpolator(interpolator)
        animator.addUpdateListener({
            val value = it.animatedValue as BigDecimal
            textView.text = mPrefixString + format(value) + mPostfixString
        })
        for (listener in animatorUpdaterListeners) {
            animator.addUpdateListener(listener)
        }
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                textView.text = mPrefixString + format(BigDecimal(mNumEnd)) + mPostfixString
            }
        })
        for (listener in animatorListeners) {
            animator.addListener(listener)
        }
        animator.start()
    }

    /**
     * 格式化 BigDecimal ,小数部分时保留两位小数并四舍五入
     *
     * @param bd 　BigDecimal
     * @return 格式化后的 String
     */
    private fun format(bd: BigDecimal): String {
        val pattern = StringBuilder()
        if (isInt) {
            pattern.append("#,###")
        } else {
            var length = 0
            val s1 = mNumStart.split("\\.")
            val s2 = mNumEnd.split("\\.")
            val s = if (s1.size > s2.size) s1 else s2
            if (s.size > 1) {
                // 小数部分
                val decimals = s[1]
                length = decimals.length
            }
            pattern.append("#,##0")
            if (length > 0) {
                pattern.append(".")
                for (i in 0 until length) {
                    pattern.append("0")
                }
            }
        }
        val df = DecimalFormat(pattern.toString())
        return df.format(bd)
    }

    private inner class BigDecimalEvaluator : TypeEvaluator<Any> {
        override fun evaluate(fraction: Float, startValue: Any, endValue: Any): Any {
            val start = startValue as BigDecimal
            val end = endValue as BigDecimal
            val result = end.subtract(start)
            return result.multiply(BigDecimal(fraction.toDouble())).add(start)
        }
    }
}