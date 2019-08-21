package com.silverknife.meizhi

import com.silvericekey.skutilslibrary.base.BasePresenter
import com.silvericekey.skutilslibrary.netUtils.NetCallback
import com.silvericekey.skutilslibrary.uiUtils.SKToastUtils

class MainPresenter:BasePresenter {
    private var mIView:IMainView?
    constructor(mIView:IMainView){
        this.mIView = mIView
    }

    fun getData(){
        LoginResponse.getData(object : NetCallback<LoginResponse> {
            override fun onSuccess(response: LoginResponse?) {
                SKToastUtils.showToast("登录成功")
            }
        })
    }

    override fun onDestroy() {
        mIView = null
    }
}