package com.silverknife.meizhi.features.feature_news

import android.text.TextUtils
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.silvericekey.skutilslibrary.utils.ImageLoderUtil
import com.silvericekey.skutilslibrary.utils.TimeUtil
import com.silverknife.meizhi.R

/**
 * <pre>
 *     time   : 2020/07/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class NewListAdapter : BaseQuickAdapter<NewsModel, NewsItemHolder>(R.layout.item_news) {
    override fun convert(helper: NewsItemHolder, item: NewsModel?) {
        helper.title.text = item?.title
        helper.author.text = item?.author_name
        if (!TextUtils.isEmpty(item!!.thumbnail_pic_s)) {
            ImageLoderUtil.getInstance()
                    .bindImg(mContext,
                            item.thumbnail_pic_s,
                            ImageLoderUtil.Builder().build())
                    .into(helper.img1)
            helper.img1.visibility = View.VISIBLE
        } else {
            helper.img1.visibility = View.GONE
        }
        if (!TextUtils.isEmpty(item.thumbnail_pic_s02)) {
            ImageLoderUtil.getInstance()
                    .bindImg(mContext,
                            item.thumbnail_pic_s02,
                            ImageLoderUtil.Builder().build())
                    .into(helper.img2)
            helper.img2.visibility = View.VISIBLE
        } else {
            helper.img2.visibility = View.GONE
        }
        if (!TextUtils.isEmpty(item.thumbnail_pic_s03)) {
            ImageLoderUtil.getInstance()
                    .bindImg(mContext,
                            item.thumbnail_pic_s03,
                            ImageLoderUtil.Builder().build())
                    .into(helper.img3)
            helper.img3.visibility = View.VISIBLE
        } else {
            helper.img3.visibility = View.GONE
        }
        helper.time.text = TimeUtil.getInstance().getTimeIntervalofCur(item.date)
    }
}