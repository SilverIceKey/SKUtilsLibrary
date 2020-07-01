package com.silverknife.meizhi.features.feature_gank

import com.silvericekey.skutilslibrary.base.BasePresenter

class GankPresenter : BasePresenter() {
    fun getData(onSuccess: (response: GankResponse) -> Unit) {
        GankResponse.getToday { onSuccess(it) }
    }

    override fun onDestroy() {

    }
}