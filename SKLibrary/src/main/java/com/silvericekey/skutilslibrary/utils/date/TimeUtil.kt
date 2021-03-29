package com.silvericekey.skutilslibrary.utils.date

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * <pre>
 *     time   : 2020/07/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class TimeUtil {
    val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"
    val MIN_TIME = 60*1000L
    val HOUR_TIME = MIN_TIME*60
    val DAY_TIME = HOUR_TIME*24
    companion object {
        @JvmStatic
        private var timeUtils: TimeUtil? = null

        @JvmStatic
        fun getInstance(): TimeUtil {
            if (timeUtils == null) {
                synchronized(TimeUtil::class.java, {
                    if (timeUtils == null) {
                        timeUtils = TimeUtil()
                    }
                })
            }
            return timeUtils!!
        }
    }

    /**
     * 计算到目前的时间
     */
    @SuppressLint("SimpleDateFormat")
    fun getTimeIntervalofCur(time: String): String {
        return getTimeIntervalofCur(time, SimpleDateFormat("yyyy-MM-dd HH:mm"))
    }

    /**
     * 计算到目前的时间
     */
    fun getTimeIntervalofCur(time: String, timeFormatter: DateFormat): String {
        val timeSecond: Long = timeFormatter.parse(time, ParsePosition(0)).getTime() / 1000
        return getTimeIntervalofCur(timeSecond)
    }

    /**
     * 计算到目前的时间
     */
    fun getTimeIntervalofCur(time: Long): String {
        val curTime = System.currentTimeMillis() / 1000
        val timeInterval = curTime - time
        if (timeInterval < 300) {
            return "刚刚"
        } else if (timeInterval < 60 * 60) {
            return "${timeInterval / 60}分钟前"
        } else if (timeInterval < 60 * 60 * 24) {
            return "${timeInterval / 60 / 60}小时前"
        } else if (timeInterval < 60 * 60 * 24 * 30) {
            return "${timeInterval / 60 / 60 / 24}天前"
        } else if (timeInterval < 60 * 60 * 24 * 30 * 12) {
            return "${timeInterval / 60 / 60 / 24 / 30}个月前"
        }
        return ""
    }

    /**
     * 判断是否是今天
     */
    @SuppressLint("SimpleDateFormat")
    fun isToday(date:String, simpleDateFormat:String = DEFAULT_DATE_FORMAT):Boolean{
        val simpleDateFormat = SimpleDateFormat(simpleDateFormat)
        val formatNowDate = simpleDateFormat.format(Date())
        return date.equals(formatNowDate)
    }

    /**
     * 时间偏移天数
     */
    fun offsetDay(offsetValue:Int,date:Date = Date()):Date{
        val simpleDateFormat = SimpleDateFormat(DEFAULT_DATE_FORMAT)
        val formatDate = simpleDateFormat.format(date)
        val parseDate = simpleDateFormat.parse(formatDate)
        return Date(parseDate.time+offsetValue*DAY_TIME)
    }

    /**
     * 时间偏移小时
     */
    fun offsetHour(offsetValue:Int,date:Date = Date()):Date{
        val simpleDateFormat = SimpleDateFormat(DEFAULT_DATE_FORMAT)
        val formatDate = simpleDateFormat.format(date)
        val parseDate = simpleDateFormat.parse(formatDate)
        return Date(parseDate.time+offsetValue*HOUR_TIME)
    }

    /**
     * 时间偏移分钟
     */
    fun offsetMin(offsetValue:Int,date:Date = Date()):Date{
        val simpleDateFormat = SimpleDateFormat(DEFAULT_DATE_FORMAT)
        val formatDate = simpleDateFormat.format(date)
        val parseDate = simpleDateFormat.parse(formatDate)
        return Date(parseDate.time+offsetValue*MIN_TIME)
    }

}