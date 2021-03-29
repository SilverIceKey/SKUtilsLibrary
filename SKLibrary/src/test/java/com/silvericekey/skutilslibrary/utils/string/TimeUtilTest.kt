package com.silvericekey.skutilslibrary.utils.string

import com.silvericekey.skutilslibrary.utils.QRCodeUtil
import com.silvericekey.skutilslibrary.utils.date.TimeUtil
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

class TimeUtilTest {
    val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"

    @Test
    fun isToday() {
        assertEquals(TimeUtil.getInstance().isToday("2021-03-22"),false)
        assertEquals(TimeUtil.getInstance().isToday("2021-03-23"),false)
    }

    @Test
    fun offsetDay() {
        val offsetDay = -1
        val simpleDateFormat = SimpleDateFormat(DEFAULT_DATE_FORMAT)
        val formatDate = simpleDateFormat.format(Date())
        val splitDate = formatDate.split("-")
        val day = splitDate[2].toInt() - offsetDay
        val parseDate = simpleDateFormat.parse("${splitDate[0]}-${splitDate[1]}-${day}")
        println("${splitDate[0]}-${splitDate[1]}-${day}")
        println(parseDate.time)
        assertEquals(TimeUtil.getInstance().offsetDay(offsetDay).time,parseDate.time)
    }
}