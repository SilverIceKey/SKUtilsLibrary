package com.silverknife.meizhi.features.feature_gank

import android.view.View
import android.widget.ImageView
import com.chad.library.adapter.base.BaseViewHolder
import com.silverknife.meizhi.R

class GankItemImageHolder(view: View?) : BaseViewHolder(view) {
    var image: ImageView = view!!.findViewById(R.id.image)
}