package com.silvericekey.skutilslibrary.utils

import java.text.DateFormat
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

/**
 * <pre>
 *     time   : 2020/07/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class TimeUtils {
    companion object{
        @JvmStatic
        private var timeUtils:TimeUtils? = null
        @JvmStatic
        fun  getInstance():TimeUtils{
            if (timeUtils==null){
                synchronized(TimeUtils::class.java,{
                    if (timeUtils==null){
                        timeUtils = TimeUtils()
                    }
                })
            }
            return timeUtils!!
        }
    }

    fun getTimeIntervalofCur(time: String):String{
        return getTimeIntervalofCur(time,SimpleDateFormat("yyyy-MM-dd HH:mm"))
    }
    fun getTimeIntervalofCur(time: String,timeFormatter: DateFormat):String{
        val time: Long = timeFormatter.parse(time, ParsePosition(0)).getTime() / 1000
        return getTimeIntervalofCur(time)
    }
    fun getTimeIntervalofCur(time: Long):String{
        var curTime = System.currentTimeMillis()/1000
        var timeInterval = curTime-time
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