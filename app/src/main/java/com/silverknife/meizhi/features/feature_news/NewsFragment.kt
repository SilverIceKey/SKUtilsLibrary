package com.silverknife.meizhi.features.feature_news

import android.view.View
import androidx.core.content.ContextCompat
import com.negier.gluetablayout.GlueTabLayout
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silverknife.meizhi.R
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment<NewsPresenter>() {
    val mNewsType = arrayListOf("头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚")
    val mNewsTypeNet = arrayListOf("top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing", "shishang")
    var newsAdapter: NewsAdapter? = null

    override fun getLayoutID(): Int {
        return R.layout.fragment_news
    }

    override fun initView(view: View) {
        newsAdapter = NewsAdapter(childFragmentManager)
        newsAdapter?.pageTitles?.addAll(mNewsType)
        for (type in mNewsTypeNet) {
            newsAdapter?.pages?.add(NewsListFragment.newInstance(type))
        }
        viewpager.adapter = newsAdapter
        tab_layout.tabMode = GlueTabLayout.MODE_SCROLLABLE
        tab_layout.setTabTextColors(ContextCompat.getColor(context!!, R.color.tab_normal), ContextCompat.getColor(context!!, R.color.tab_selected))
        //GlueTabLayout 设置下划线指示器的宽度为原来的一半
        tab_layout.setTabIndicatorWidth(0.8f)
        tab_layout.setupWithViewPager(viewpager)
    }

    override fun initPresenter(): NewsPresenter {
        return NewsPresenter()
    }
}