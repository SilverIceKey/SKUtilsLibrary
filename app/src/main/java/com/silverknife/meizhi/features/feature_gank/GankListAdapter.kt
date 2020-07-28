package com.silverknife.meizhi.features.feature_gank

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.silvericekey.skutilslibrary.utils.ImageLoderUtil
import com.silverknife.meizhi.R

class GankListAdapter : BaseQuickAdapter<GankItemModel, GankListHolder>(R.layout.item_gank_list) {
    var listener: onImagesClickListener? = null
    override fun convert(helper: GankListHolder, item: GankItemModel?) {
        helper.itemView.elevation = 2f
        helper.contentTitle.text = item!!.desc
        if (item.images != null) {
            var imagesAdapter = GankItemImagesAdapter()
            helper.images.layoutManager = GridLayoutManager(ActivityUtils.getTopActivity(), 3)
            imagesAdapter.bindToRecyclerView(helper.images)
            imagesAdapter.setNewData(item.images)
            helper.images.visibility = View.VISIBLE
            imagesAdapter.setOnItemChildClickListener { adapter, view, position ->
                listener?.onImagesClick(adapter.getItem(position) as String)
            }
            helper.images.viewTreeObserver.addOnGlobalLayoutListener {
                helper.images.postDelayed({
                    helper.images.isLayoutFrozen = true
                },300)
            }
//            helper.images.isLayoutFrozen = true
        } else {
            helper.images.visibility = View.GONE
            helper.images.viewTreeObserver.addOnGlobalLayoutListener {
                helper.images.postDelayed({
                    helper.images.isLayoutFrozen = true
                },300)
            }
//            helper.images.isLayoutFrozen = true
        }
        if (item.url.endsWith(".jpg")) {
            var option = ImageLoderUtil.Builder().build()
            ImageLoderUtil.getInstance().bindImg(ActivityUtils.getTopActivity(), item.url, option).into(helper.image)
            helper.image.visibility = View.VISIBLE
            helper.addOnClickListener(helper.image.id)
        } else {
            helper.image.visibility = View.GONE
        }
        if (item.used) {
            helper.isNew.visibility = View.VISIBLE
        } else {
            helper.isNew.visibility = View.GONE
        }
        helper.who.text = item.who
        helper.time.text = item.publishedAt.split("T")[0]
    }

    interface onImagesClickListener {
        fun onImagesClick(url: String)
    }
}