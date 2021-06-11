package com.silvericekey.skutilslibrary.utils.card

import java.util.*

/**
 * 身份证号码检查
 */
object CardCheckUtil {
    /**
     * 检查是否成年
     */
    fun checkAdult(date: Date):Boolean{
        val current = Calendar.getInstance()
        val birthDay = Calendar.getInstance()
        birthDay.time = date
        val year = current[Calendar.YEAR] - birthDay[Calendar.YEAR]
        if (year > 18) {
            return true
        } else if (year < 18) {
            return false
        }
        // 如果年相等，就比较月份
        // 如果年相等，就比较月份
        val month = current[Calendar.MONTH] - birthDay[Calendar.MONTH]
        if (month > 0) {
            return true
        } else if (month < 0) {
            return false
        }
        // 如果月也相等，就比较天
        // 如果月也相等，就比较天
        val day = current[Calendar.DAY_OF_MONTH] - birthDay[Calendar.DAY_OF_MONTH]
        return day >= 0
    }
}