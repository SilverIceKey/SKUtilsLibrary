package com.silverknife.meizhi.mvp.ui.activity

import android.text.TextUtils
import androidx.core.app.ActivityCompat
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silvericekey.skutilslibrary.utils.ImageLoderUtil
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.PhotoViewPresenter
import kotlinx.android.synthetic.main.activity_gank_detail.*
import kotlinx.android.synthetic.main.activity_gank_detail.back
import kotlinx.android.synthetic.main.activity_photo_view.*
import kotlinx.android.synthetic.main.gank_list_item.*
import kotlinx.android.synthetic.main.gank_list_item.content_title
import kotlinx.android.synthetic.main.gank_list_item.image
import me.jessyan.autosize.utils.AutoSizeUtils

class PhotoViewActivity : BaseActivity<PhotoViewPresenter>() {
    companion object {
        val IMAGE = "image"
        val TITLE = "title"
    }

    override fun getLayoutID(): Int {
        return R.layout.activity_photo_view
    }

    override fun initTransitionViews(){
        addTransitionName(image, IMAGE)
        addTransitionName(content_title, TITLE)
    }

    override fun initView() {
        var title = intent.getStringExtra("title")
        if (!TextUtils.isEmpty(title)) {
            content_title.text = title
        } else {
            content_title.text = "图片"
        }
        back.setOnClickListener { ActivityCompat.finishAfterTransition(this) }
        image.isEnabled = true
        var url = intent.getStringExtra("url")
        ImageLoderUtil.bindImg(this, url, ImageLoderUtil.Builder().build()).override(AutoSizeUtils.dp2px(this,360f),AutoSizeUtils.dp2px(this,480f)).into(image)
    }

    override fun initPresenter(): PhotoViewPresenter {
        return PhotoViewPresenter()
    }
}