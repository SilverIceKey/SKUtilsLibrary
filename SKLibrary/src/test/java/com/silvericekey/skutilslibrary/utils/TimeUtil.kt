package com.silvericekey.skutilslibrary.utils

import com.silvericekey.skutilslibrary.utils.string.TimeUtil
import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*


class TimeUtil {
    val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"
    @Test
    fun isSameDay(){
       
    }
    @Test
    fun offsetDay(){
        val simpleDateFormat = SimpleDateFormat(DEFAULT_DATE_FORMAT)
        val formatDate = simpleDateFormat.format(Date())
        val splitDate = formatDate.split("-")
        val day = splitDate[2].toInt() - 1
        val parseDate = simpleDateFormat.parse("${splitDate[0]}-${splitDate[1]}-${day}")
        Assert.assertEquals(TimeUtil.getInstance().offsetDay(-1),parseDate)
    }
}