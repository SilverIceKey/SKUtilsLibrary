package com.silvericekey.skutilslibrary.netUtils

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.ByteString
import kotlin.concurrent.timerTask

class MockWebServerUtil {
    var mockWebServer:MockWebServer?=null
    var msgCount=0
    constructor(){
        mockWebServer = MockWebServer()
        mockWebServer!!.enqueue(MockResponse().withWebSocketUpgrade(object : WebSocketListener(){
            override fun onOpen(webSocket: WebSocket, response: Response) {
                super.onOpen(webSocket, response)
                println("server onOpen")
                println("server request header:" + response.request.headers)
                println("server response header:" + response.headers)
                println("server response:" + response)
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                println("server onMessage")
                println("message:" + text)
                //接受到5条信息后，关闭消息定时发送器
                if(msgCount == 5){
                    webSocket.close(1000, "close by server")
                    return
                }
                webSocket.send("response-" + text)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                super.onMessage(webSocket, bytes)
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosing(webSocket, code, reason)
                println("server onClosing")
                println("code:" + code + " reason:" + reason)
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                super.onClosed(webSocket, code, reason)
                println("server onClosed")
                println("code:" + code + " reason:" + reason)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                super.onFailure(webSocket, t, response)
                //出现异常会进入此回调
                println("server onFailure")
                println("throwable:" + t)
                println("response:" + response)
            }
        }))
    }
}