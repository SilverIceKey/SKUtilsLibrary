package com.silverknife.meizhi

import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.base.BasePresenter
import com.silvericekey.skutilslibrary.utils.HttpUtil

class MainPresenter : BasePresenter {
    private var mIView: IMainView?

    constructor(mIView: IMainView) {
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

    override fun onDestroy() {
        mIView = null
    }
}