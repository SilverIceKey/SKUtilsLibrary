package com.silverknife.meizhi

import com.silvericekey.skutilslibrary.netUtils.HttpUtil
import com.silvericekey.skutilslibrary.netUtils.MockWebServerUtil
import org.junit.Test

class TestWebSocket {

    @Test
    fun testWebSocket(){
//        val mockWebServerUtil = MockWebServerUtil()
        HttpUtil.webSocketTest()
    }
}