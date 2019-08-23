package com.silverknife.meizhi

import com.silvericekey.skutilslibrary.base.BasePresenter
import com.silvericekey.skutilslibrary.netUtils.HttpUtil
import com.silvericekey.skutilslibrary.netUtils.NetCallback
import com.silvericekey.skutilslibrary.uiUtils.SKToastUtil

class MainPresenter:BasePresenter {
    private var mIView:IMainView?
    constructor(mIView:IMainView){
        this.mIView = mIView
    }

    fun getData(){
        LoginResponse.getData(object : NetCallback<LoginResponse> {
            override fun onError(throwable: Throwable) {
                SKToastUtil.showToast("登录失败")
                mIView!!.setResponse("登录失败")
            }

            override fun onSuccess(response: LoginResponse?) {
                SKToastUtil.showToast("登录成功")
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