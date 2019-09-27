package com.silverknife.meizhi.mvp.adapter

import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.silvericekey.skutilslibrary.utils.ImageLoderUtil
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.ui.holder.GankItemImageHolder

class GankItemImagesAdapter : BaseQuickAdapter<String, GankItemImageHolder>(R.layout.gank_item_image) {
    override fun convert(helper: GankItemImageHolder, item: String?) {
        var option = ImageLoderUtil.Builder().build()
        ImageLoderUtil.bindImg(ActivityUtils.getTopActivity(), item.toString(), option).into(helper.image)
    }
}