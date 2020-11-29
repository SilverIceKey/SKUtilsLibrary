package com.silverknife.meizhi.mvp.model

class GankResponse {
    var category: ArrayList<String>? = null
    var error: Boolean = false
    var results: Map<String,ArrayList<GankItemModel>>? = null

    companion object {
        @JvmStatic
        fun getToday(onSuccess: (response: GankResponse) -> Unit) {

        }
    }
}