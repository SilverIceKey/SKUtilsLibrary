package com.silverknife.meizhi.features.feature_news

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * <pre>
 *     time   : 2020/07/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class NewsItemHolder(view: View) : BaseViewHolder(view) {
    var title: TextView
    var img1: ImageView
    var img2: ImageView
    var img3: ImageView
    var author: TextView
    var time: TextView

    init {
        title = view.title
        img1 = view.img1
        img2 = view.img2
        img3 = view.img3
        author = view.author
        time = view.time
    }
}