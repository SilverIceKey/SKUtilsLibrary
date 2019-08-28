package com.silverknife.meizhi

import com.silvericekey.skutilslibrary.utils.HttpUtil
import org.junit.Test

class TestWebSocket {

    @Test
    fun testWebSocket(){
//        val mockWebServerUtil = MockWebServerUtil()
        HttpUtil.webSocketTest()
    }
}