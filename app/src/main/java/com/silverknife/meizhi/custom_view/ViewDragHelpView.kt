package com.silverknife.meizhi.mvp.ui.view

import android.content.Context
import android.graphics.Point
import android.graphics.PointF
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import com.silverknife.meizhi.R


class ViewDragHelpView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    var dragHelper: ViewDragHelper? = null
    var wm: WindowManager
    var displayWidth: Int
    var displayHeight: Int
    var point: Point? = null
    var isUp: Boolean = false

    init {
        wm = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val view = LayoutInflater.from(context).inflate(R.layout.viewdraghelp_view, null)
        point = Point()
        wm.defaultDisplay.getRealSize(point)
        displayWidth = point!!.x
        displayHeight = point!!.y
        dragHelper = ViewDragHelper.create(this, 10.0f, object : ViewDragHelper.Callback() {
            override fun tryCaptureView(child: View, pointerId: Int): Boolean {
                return true
            }

            override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
                println("top:$top")
                if (top < 0) {
                    return 0
                }
                return top
            }

            override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
                super.onViewPositionChanged(changedView, left, top, dx, dy)
                if (dy < 0) {
                    isUp = true
                } else {
                    isUp = false
                }
            }

            override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
//                super.onViewReleased(releasedChild, xvel, yvel)
                if (releasedChild.top > displayHeight / 6 && !isUp) {
                    dragHelper!!.settleCapturedViewAt(0, displayHeight * 4 / 5)
                    postInvalidate()
                } else if (isUp) {
                    dragHelper!!.settleCapturedViewAt(0, 0)
                    postInvalidate()
                } else {
                    dragHelper!!.settleCapturedViewAt(0, 0)
                    postInvalidate()
                }
            }
        })
        addView(view)
        var layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
//        layoutParams.width = 200
//        layoutParams.height = 400
        view.layoutParams = layoutParams
    }

    override fun computeScroll() {
        super.computeScroll()
        if (dragHelper?.continueSettling(true)!!) {
            invalidate()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return dragHelper!!.shouldInterceptTouchEvent(ev!!)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        dragHelper!!.processTouchEvent(event!!)
        return true
    }
}