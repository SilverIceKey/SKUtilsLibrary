package com.silvericekey.skutilslibrary.interpolator;

import android.animation.TimeInterpolator;

/**
 * 动画插值器
 */
public class JellyInterpolator implements TimeInterpolator {
    // 因子数值越小振动频率越高
    private float factor = 0.15f;

    public JellyInterpolator(float factor) {
        this.factor = factor;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (Math.pow(2.0, (-10.0 * input)) * Math.sin((input - factor / 4.0) * (2.0 * Math.PI) / factor) + 1.0);
    }
}
