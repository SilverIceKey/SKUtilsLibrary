package com.silverknife.meizhi.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.silverknife.meizhi.ICallbacklInterface
import com.silverknife.meizhi.IManagerInterface
import com.silverknife.meizhi.features.feature_main.MainActivity

/**
 * <pre>
 *     time   : 2020/07/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CustomService: Service() {
    var mICallbacklInterface:ICallbacklInterface?=null
    var mBinder = object : IManagerInterface.Stub(){
        override fun test() {
            Log.d("debug","test")
            mICallbacklInterface?.callback();
        }

        override fun setCallBack(callback: ICallbacklInterface?) {
            Log.d("debug","setCallBack")
            mICallbacklInterface = callback
        }

    }
    override fun onBind(intent: Intent?): IBinder? {
        Log.d("debug","onBind")
        MainActivity.mainHandler?.sendEmptyMessage(0)
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("debug","onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("debug","onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }
}