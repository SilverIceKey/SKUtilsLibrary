package com.silverknife.meizhi.mvp.adapter

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.silvericekey.skutilslibrary.utils.ImageLoderUtil
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.model.GankItemModel
import com.silverknife.meizhi.mvp.ui.holder.GankListHolder

class GankListAdapter : BaseQuickAdapter<GankItemModel, GankListHolder>(R.layout.gank_list_item) {
    override fun convert(helper: GankListHolder, item: GankItemModel?) {
        helper.contentTitle.text = item!!.desc
        if (item.images != null) {
            var imagesAdapter = GankItemImagesAdapter()
            helper.images.layoutManager = GridLayoutManager(ActivityUtils.getTopActivity(), 3)
            imagesAdapter.bindToRecyclerView(helper.images)
            imagesAdapter.setNewData(item.images)
            helper.images.visibility = View.VISIBLE
        } else {
            helper.images.visibility = View.GONE
        }
        if (item.url.endsWith(".jpg")){
            var option = ImageLoderUtil.Builder().build()
            ImageLoderUtil.bindImg(ActivityUtils.getTopActivity(), item.toString(), option).into(helper.image)
            helper.image.visibility = View.VISIBLE
        }else{
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
}