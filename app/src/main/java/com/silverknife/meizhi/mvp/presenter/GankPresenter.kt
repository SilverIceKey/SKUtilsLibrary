package com.silverknife.meizhi.mvp.presenter

import com.silvericekey.skutilslibrary.base.BasePresenter
import com.silverknife.meizhi.mvp.model.GankResponse

class GankPresenter : BasePresenter() {
    fun getData(onSuccess: (response: GankResponse) -> Unit) {
        GankResponse.getToday { onSuccess(it) }
    }

    override fun onDestroy() {

    }
}