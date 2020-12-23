package com.silvericekey.skutilslibrary.utils.string

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.ParsePosition
import java.text.SimpleDateFormat

/**
 * <pre>
 *     time   : 2020/07/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class TimeUtil {
    companion object{
        @JvmStatic
        private var timeUtils: TimeUtil? = null
        @JvmStatic
        fun  getInstance(): TimeUtil {
            if (timeUtils ==null){
                synchronized(TimeUtil::class.java,{
                    if (timeUtils ==null){
                        timeUtils = TimeUtil()
                    }
                })
            }
            return timeUtils!!
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getTimeIntervalofCur(time: String):String{
        return getTimeIntervalofCur(time,SimpleDateFormat("yyyy-MM-dd HH:mm"))
    }
    fun getTimeIntervalofCur(time: String,timeFormatter: DateFormat):String{
        val timeSecond: Long = timeFormatter.parse(time, ParsePosition(0)).getTime() / 1000
        return getTimeIntervalofCur(timeSecond)
    }
    fun getTimeIntervalofCur(time: Long):String{
        val curTime = System.currentTimeMillis()/1000
        val timeInterval = curTime-time
        if (timeInterval<300){
            return "刚刚"
        }else if (timeInterval<60*60){
            return "${timeInterval/60}分钟前"
        }else if (timeInterval<60*60*24){
            return "${timeInterval/60/60}小时前"
        }else if (timeInterval<60*60*24*30){
            return "${timeInterval/60/60/24}天前"
        }else if (timeInterval<60*60*24*30*12){
            return "${timeInterval/60/60/24/30}个月前"
        }
        return ""
    }
}