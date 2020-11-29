package com.silverknife.meizhi.features.feature_gank

import com.silvericekey.skutilslibrary.base.BasePresenter

class GankListPresenter : BasePresenter() {
    fun getList(category: String, onSuccess: (response: ArrayList<GankItemModel>) -> Unit) {
        GankResponse.getToday { onSuccess(it.results?.get(category)!!) }
    }

    override fun onDestroy() {

    }
}