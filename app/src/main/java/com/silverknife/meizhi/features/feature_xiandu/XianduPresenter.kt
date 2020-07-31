package com.silverknife.meizhi.features.feature_xiandu

import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.FileUtils
import com.silvericekey.skutilslibrary.base.BasePresenter
import com.silvericekey.skutilslibrary.utils.FileIOUtil
import com.silverknife.meizhi.App

class XianduPresenter: BasePresenter() {
    fun getData():String{
        return FileIOUtil.getInstance().readFile2StringFromAssets("data.json")
    }

    override fun onDestroy() {

    }
}