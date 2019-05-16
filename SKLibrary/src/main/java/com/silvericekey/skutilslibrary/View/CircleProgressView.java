package com.silvericekey.skutilslibrary.View;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CircleProgressView extends View {
    private float angle = 0;
    private ProgressListener mListener;
    private float mMaxAngle = 360;
    private int mDuration = 2000;
    private List<Integer> mColors;
    private List<Float> mAngles;
    private Context mContext;
    private float mStartAngle = -90;
    private int mStartColor;
    private int mEndColor;
    private int mWidth;
    private int mHeight;
    private float mStylePadding = 0;

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, @NonNull AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressView(Context context, @NonNull AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = measureWidth(widthMeasureSpec) + getPaddingLeft() + getPaddingRight();
        mHeight = measureWidth(heightMeasureSpec) + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(mWidth, mHeight);
    }

    /**
     * 设置进度监听
     *
     * @param mListener
     */
    public void setOnProgressListener(ProgressListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 设置最大角度(最大圆弧)
     *
     * @param mMaxAngle
     */
    public void setMaxAngle(float mMaxAngle) {
        this.mMaxAngle = mMaxAngle;
    }

    /**
     * 设置最大圆弧的动画完成时间
     *
     * @param mDuration
     */
    public void setDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    /**
     * 颜色为int类型时调用此方法
     * 同时设置{@link #setSColorAndAngle(HashMap)}时默认以后设置的为准
     *
     * @param mIColorAndAngle
     */
    public void setIColorAndAngle(HashMap<Integer, Float> mIColorAndAngle) {
        mColors = new ArrayList();
        mAngles = new ArrayList();
        Iterator<Map.Entry<Integer, Float>> entries = mIColorAndAngle.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Float> entry = entries.next();
            mColors.add(ContextCompat.getColor(mContext, entry.getKey()));
            mAngles.add(entry.getValue());
        }
    }

    /**
     * 颜色为String类型时调用此方法
     * 同时设置{@link #setIColorAndAngle(HashMap)}时默认以后设置的为准
     *
     * @param mSColorAndAngle
     */
    public void setSColorAndAngle(HashMap<String, Float> mSColorAndAngle) {
        mColors = new ArrayList();
        mAngles = new ArrayList();
        Iterator<Map.Entry<String, Float>> entries = mSColorAndAngle.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Float> entry = entries.next();
            mColors.add(Color.parseColor(entry.getKey()));
            mAngles.add(entry.getValue());
        }
    }

    /**
     * 渐变色设置
     *
     * @param startColor 起始颜色
     * @param endColor   结束颜色
     * @param isReverse  是否倒序
     */
    public void setStartColorAndEndColor(String startColor, String endColor, boolean isReverse) {
        setStartColorAndEndColor(Color.parseColor(startColor), Color.parseColor(endColor), isReverse);
    }

    /**
     * 渐变色设置
     *
     * @param startColor 起始颜色
     * @param endColor   结束颜色
     * @param isReverse  是否倒序
     */
    @SuppressLint("SupportAnnotationUsage")
    @ColorInt
    public void setStartColorAndEndColor(int startColor, int endColor, boolean isReverse) {
        mColors = new ArrayList<>();
        mAngles = new ArrayList<>();
        if (isReverse) {
            mStartColor = endColor;
            mEndColor = startColor;
        } else {
            mStartColor = startColor;
            mEndColor = endColor;
        }
    }

    /**
     * 设置起始位置
     *
     * @param mStartAngle
     */
    public void setStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle;
    }

    /**
     * 动画开始
     */
    public void startAnime() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "angle", 0, mMaxAngle);
        animator.setDuration(mDuration);
        // 执行动画
        animator.start();
    }

    /**
     * 设置角度(圆弧)为动画调用，一般不用使用
     *
     * @param angle
     */
    public void setAngle(float angle) {
        this.angle = angle;
        invalidate();
    }

    /**
     * 获取圆弧角度
     *
     * @return
     */
    public float getAngle() {
        return angle;
    }

    private Paint.Cap mPaintCap = Paint.Cap.BUTT;

    /**
     * 设置进度条的类型
     * @param mPaintCap
     */
    public void setPaintCap(Paint.Cap mPaintCap) {
        this.mPaintCap = mPaintCap;
    }

    private Paint.Style mPaintStyle = Paint.Style.STROKE;

    /**
     * 设置圆形的类型
     * @param mPaintStyle
     */
    public void setPaintStyle(Paint.Style mPaintStyle) {
        this.mPaintStyle = mPaintStyle;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(mPaintStyle);
        paint.setStrokeWidth(40);
        paint.setStrokeCap(mPaintCap);
        drawArc(canvas, paint, angle);
        canvas.save();
    }

    private float mTotalAngle = 0;

    private void drawArc(Canvas canvas, Paint paint, float angle) {
        switch (paint.getStyle()) {
            case FILL:
                mStylePadding = 0f;
                break;
            case STROKE:
                mStylePadding = 20f;
                break;
            case FILL_AND_STROKE:
                mStylePadding = 0f;
                break;
        }
        if (mColors != null && mAngles != null && mColors.size() != 0 && mAngles.size() != 0) {
            for (int i = 0; i < mAngles.size(); i++) {
                if (angle > getTotalAngle(i)) {
                    paint.setColor(mColors.get(i));
                    if (angle >= mAngles.get(i) + getTotalAngle(i)) {
                        canvas.drawArc(getPaddingLeft()+mStylePadding, getPaddingTop()+mStylePadding, mWidth - getPaddingRight()-mStylePadding, mHeight - getPaddingBottom()-mStylePadding, mStartAngle + getTotalAngle(i), mAngles.get(i), false, paint);
                    } else {
                        canvas.drawArc(getPaddingLeft()+mStylePadding, getPaddingTop()+mStylePadding, mWidth - getPaddingRight()-mStylePadding, mHeight - getPaddingBottom()-mStylePadding, mStartAngle + getTotalAngle(i), angle - getTotalAngle(i), false, paint);
                        break;
                    }
                }
            }
        } else {
            Matrix matrix = new Matrix();
            matrix.setRotate(mStartAngle, (getPaddingLeft() + mWidth - getPaddingRight()-mStylePadding) / 2, (getPaddingTop() + mWidth - getPaddingBottom()-mStylePadding) / 2);
            SweepGradient sweepGradient = new SweepGradient((getPaddingLeft() + mWidth - getPaddingRight()-mStylePadding) / 2, (getPaddingTop() + mWidth - getPaddingBottom()-mStylePadding) / 2, mStartColor, mEndColor);
            sweepGradient.setLocalMatrix(matrix);
            paint.setShader(sweepGradient);
            canvas.drawArc(getPaddingLeft()+mStylePadding, getPaddingTop()+mStylePadding, mWidth - getPaddingRight()-mStylePadding, mHeight - getPaddingBottom()-mStylePadding, mStartAngle, mTotalAngle, false, paint);
            canvas.drawArc(getPaddingLeft()+mStylePadding, getPaddingTop()+mStylePadding, mWidth - getPaddingRight()-mStylePadding, mHeight - getPaddingBottom()-mStylePadding, mStartAngle + mTotalAngle, angle - mTotalAngle, false, paint);
            mTotalAngle += angle - mTotalAngle;
        }
        if (mListener != null) {
            mListener.progress(angle / mMaxAngle * 100);
        }
    }

    private float getTotalAngle(int i) {
        float totalAngle = 0;
        for (int j = 0; j < i; j++) {
            totalAngle += mAngles.get(j);
        }
        return totalAngle;
    }

    /**
     * 进度监听接口
     */
    public interface ProgressListener {
        void progress(float progress);
    }

    private int measureWidth(int measureSpec) {
        int result = 400;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 400;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result, specSize);
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }
}
