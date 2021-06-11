package com.silvericekey.skutilslibrary.utils.date

import junit.framework.TestCase
import java.text.SimpleDateFormat
import java.util.*

class TimeUtilTest : TestCase() {

    fun testGetDEFAULT_DATE_FORMAT() {
        assertEquals("yyyy-MM-dd", TimeUtil.getInstance().DEFAULT_DATE_FORMAT)
    }

    fun testGetMIN_TIME() {
        assertEquals(60 * 1000L, TimeUtil.getInstance().MIN_TIME)
    }

    fun testGetHOUR_TIME() {
        assertEquals(60 * 60 * 1000L, TimeUtil.getInstance().HOUR_TIME)
    }

    fun testGetDAY_TIME() {
        assertEquals(24 * 60 * 60 * 1000L, TimeUtil.getInstance().DAY_TIME)
    }

    fun testTestGetTimeIntervalofCur1() {
        assertEquals("1天前", TimeUtil.getInstance().getTimeIntervalofCur("2021-06-10 00:00"))
    }

    fun testIsToday() {
        assertEquals(true, TimeUtil.getInstance().isToday("2021-06-11", "yyyy-MM-dd"))
    }

    fun testOffsetDay() {
        assertEquals(SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse("2021-06-10"),
                TimeUtil.getInstance().offsetDay(-1, Date()))
    }

    fun testOffsetHour() {
        assertEquals(SimpleDateFormat("yyyy-MM-dd HH", Locale.CHINA).parse("2021-06-11 21"),
                TimeUtil.getInstance().offsetHour(-1, Date()))
    }

    fun testOffsetMin() {
        assertEquals(SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).parse("2021-06-11 22:18"),
                TimeUtil.getInstance().offsetMin(-3, Date()))
    }

    fun testNowString() {
        assertEquals("2021-06-11", TimeUtil.getInstance().nowString())
    }

    fun testToday() {
        assertEquals(SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse("2021-06-11"), TimeUtil.getInstance().today())
    }

    fun testIsTimeBefore() {
        assertEquals(true, TimeUtil.getInstance().isTimeBefore("2021-06-10","2021-06-11"))
    }

    fun testIsTimeBeforeToday() {
        assertEquals(true, TimeUtil.getInstance().isTimeBeforeToday("2021-06-10"))
    }

    fun testGetDateOnly() {
        assertEquals(SimpleDateFormat("yyyy-MM-dd", Locale.CHINA).parse("2021-06-11"), TimeUtil.getInstance().getDateOnly(Date()))
    }

    fun testCalcDayNum() {
        assertEquals(-1, TimeUtil.getInstance().calcDayNum("2021-06-10","2021-06-11"))
    }
}