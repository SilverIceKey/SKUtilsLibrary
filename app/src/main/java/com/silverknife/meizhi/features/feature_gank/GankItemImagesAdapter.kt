package com.silverknife.meizhi.features.feature_gank

import com.blankj.utilcode.util.ActivityUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.silvericekey.skutilslibrary.utils.image.ImageLoderUtil
import com.silverknife.meizhi.R

class GankItemImagesAdapter : BaseQuickAdapter<String, GankItemImageHolder>(R.layout.item_gank_image) {
    override fun convert(helper: GankItemImageHolder, item: String?) {
        var option = ImageLoderUtil.Builder().build()
        ImageLoderUtil.getInstance().bindImg(ActivityUtils.getTopActivity(), item.toString(), option).into(helper.image)
        helper.addOnClickListener(helper.image.id)
    }
}