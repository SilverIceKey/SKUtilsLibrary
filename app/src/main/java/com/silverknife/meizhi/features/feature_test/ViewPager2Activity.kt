package com.silverknife.meizhi.mvp.ui.activity

import android.graphics.Rect
import android.graphics.RectF
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.features.feature_test.TabFragment
import com.silverknife.meizhi.mvp.presenter.ViewPager2Presenter
import kotlinx.android.synthetic.main.activity_viewpager2.*

class ViewPager2Activity : BaseActivity<ViewPager2Presenter>() {
    private val weibo = arrayOf("精选", "推荐", "榜单", "故事", "综艺", "剧集", "VLOG", "明星", "电影")
    override fun getLayoutID(): Int {
        return R.layout.activity_viewpager2
    }
    override fun initView() {
        viewpager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return weibo.size
            }

            override fun createFragment(position: Int): Fragment {
                return TabFragment.newInstance(weibo[position])
            }

        }
    }

    override fun initPresenter(): ViewPager2Presenter {
        return ViewPager2Presenter()
    }
}