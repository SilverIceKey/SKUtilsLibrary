package com.silverknife.meizhi.features.feature_gank

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseViewHolder
import com.silverknife.meizhi.R

class GankListHolder(view: View?) : BaseViewHolder(view) {
    var contentTitle: TextView = view!!.findViewById(R.id.content_title)
    var images: RecyclerView = view!!.findViewById(R.id.images)
    var image: ImageView = view!!.findViewById(R.id.image)
    var isNew: TextView = view!!.findViewById(R.id.is_new)
    var who: TextView = view!!.findViewById(R.id.who)
    var time: TextView = view!!.findViewById(R.id.time)
}