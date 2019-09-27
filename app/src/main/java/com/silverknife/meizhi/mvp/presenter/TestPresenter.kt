package com.silverknife.meizhi.mvp.presenter

import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.base.BasePresenter
import com.silvericekey.skutilslibrary.utils.HttpUtil
import com.silverknife.meizhi.mvp.model.LoginResponse
import com.silverknife.meizhi.mvp.ui.interfaces.ITestView

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