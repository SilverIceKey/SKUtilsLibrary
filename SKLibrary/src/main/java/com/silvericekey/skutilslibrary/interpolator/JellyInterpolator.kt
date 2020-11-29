package com.silvericekey.skutilslibrary.interpolator

import android.animation.TimeInterpolator

class JellyInterpolator : TimeInterpolator {
    // 因子数值越小振动频率越高
    private var factor: Float = 0.15f

    constructor(factor: Float) {
        this.factor = factor
    }

    override fun getInterpolation(input: Float): Float {
        return (Math.pow(2.0, (-10 * input).toDouble()) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1).toFloat()
    }
}