package com.silverknife.meizhi.features.feature_test

import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.base.BasePresenter
import com.silvericekey.skutilslibrary.utils.HttpUtil

class TestPresenter : BasePresenter {
    override fun onDestroy() {
        mIView = null
    }

    private var mIView: ITestView?

    constructor(mIView: ITestView) {
        this.mIView = mIView
    }

    fun getData() {
        LoginResponse.getData({
            ToastUtils.showShort("登录成功")
            mIView!!.setResponse("登录成功")
        }, {
            ToastUtils.showShort("登录失败")
            mIView!!.setResponse("登录失败")
        })
    }

    fun webSocketRequest() {
        HttpUtil.webSocketTest()
    }
}