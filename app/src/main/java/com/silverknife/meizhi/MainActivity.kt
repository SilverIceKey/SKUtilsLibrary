package com.silverknife.meizhi

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.Looper
import android.view.View

import com.silvericekey.skutilslibrary.IOUtils.ThreadHandle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.text).setOnClickListener({ v -> printHandlerLooper() })
        printHandlerLooper()
    }


    private fun printHandlerLooper() {
        ThreadHandle.runOnIOThread { System.out.println("io Looper" + Looper.myLooper()!!) }
        ThreadHandle.runOnUiThread { System.out.println("ui Looper" + Looper.myLooper()!!) }
    }
}
