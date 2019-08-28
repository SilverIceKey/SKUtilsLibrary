package com.silverknife.meizhi

import com.blankj.utilcode.util.ToastUtils
import com.silvericekey.skutilslibrary.base.BasePresenter
import com.silvericekey.skutilslibrary.utils.HttpUtil
import com.silvericekey.skutilslibrary.net.NetCallback

class MainPresenter:BasePresenter {
    private var mIView:IMainView?
    constructor(mIView:IMainView){
        this.mIView = mIView
    }

    fun getData(){
        LoginResponse.getData(object : NetCallback<LoginResponse> {
            override fun onError(throwable: Throwable) {
                ToastUtils.showShort("登录失败")
                mIView!!.setResponse("登录失败")
            }

            override fun onSuccess(response: LoginResponse?) {
                ToastUtils.showShort("登录成功")
                mIView!!.setResponse("登录成功")
            }
        })
    }

    fun webSocketRequest(){
        HttpUtil.webSocketTest()
    }

    override fun onDestroy() {
        mIView = null
    }
}