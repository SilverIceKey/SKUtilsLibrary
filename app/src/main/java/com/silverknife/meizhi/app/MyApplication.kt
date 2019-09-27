package com.silverknife.meizhi.app

import com.silvericekey.skutilslibrary.SKUtilsLibrary
import com.silvericekey.skutilslibrary.base.BaseApplication
import com.silvericekey.skutilslibrary.utils.HttpUtil

/**
 * Created by silverknife on 2017/12/13.
 */

class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        SKUtilsLibrary.init(this)
        HttpUtil.init("http://football.dooksport.com/")
    }

}
