package com.silverknife.meizhi.custom_view

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * <pre>
 *     time   : 2020/07/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class NumberTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val TextSize = 80f
    private var mNumberPaint: TextPaint
    private var mWidth: Int
    private var mHeight: Int

    init {
        mNumberPaint = TextPaint()
        mNumberPaint.isAntiAlias = true
        mNumberPaint.textSize = TextSize
        mNumberPaint.flags = Paint.ANTI_ALIAS_FLAG
        mNumberPaint.color = Color.BLACK
        mNumberPaint.style = Paint.Style.FILL
        mNumberPaint.strokeWidth = 2f
        mNumberPaint.textAlign = Paint.Align.CENTER
        var dm = resources.displayMetrics
        mWidth = dm.widthPixels
        mHeight = dm.heightPixels
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var text = "Hello，World！"
        val positionX = mWidth / 2f
        val positionY = (mHeight-TextSize) / 2f
        val rectF = RectF(100f,positionY,400f,positionY+150f)
        mNumberPaint.style = Paint.Style.FILL
        mNumberPaint.color = Color.BLACK
        canvas?.drawRoundRect(rectF,10f,10f,mNumberPaint)
//        canvas?.drawText(text, positionX, positionY, mNumberPaint)
        mNumberPaint.style = Paint.Style.STROKE
        mNumberPaint.color = Color.WHITE
//        canvas?.drawText(text, positionX, positionY, mNumberPaint)
        canvas?.drawRoundRect(rectF,10f,10f,mNumberPaint)
    }
}