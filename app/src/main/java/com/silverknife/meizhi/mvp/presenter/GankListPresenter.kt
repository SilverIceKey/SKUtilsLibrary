package com.silverknife.meizhi.mvp.presenter

import com.silvericekey.skutilslibrary.base.BasePresenter
import com.silverknife.meizhi.mvp.model.GankItemModel
import com.silverknife.meizhi.mvp.model.GankResponse

class GankListPresenter : BasePresenter() {
    fun getList(category: String, onSuccess: (response: ArrayList<GankItemModel>) -> Unit) {
        GankResponse.getToday { onSuccess(it.results?.get(category)!!) }
    }

    override fun onDestroy() {

    }
}