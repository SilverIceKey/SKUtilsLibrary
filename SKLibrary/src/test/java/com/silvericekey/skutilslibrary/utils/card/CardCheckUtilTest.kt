package com.silvericekey.skutilslibrary.utils.card

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.*

class CardCheckUtilTest {

    @Test
    fun checkAdult() {
        val simpleDateFormat =
                SimpleDateFormat("yyyyMMdd") //注意月份是MM
        val date: Date = simpleDateFormat.parse("20030825")
        assertEquals(false,CardCheckUtil.checkAdult(date))
    }
}