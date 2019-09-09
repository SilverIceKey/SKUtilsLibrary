package com.silverknife.meizhi

import androidx.fragment.app.Fragment
import com.negier.gluetablayout.BasePagerAdapter
import com.negier.gluetablayout.GlueTabLayout
import com.silvericekey.skutilslibrary.base.BaseActivity
import kotlinx.android.synthetic.main.activity_tabs.*

class TabsActivity : BaseActivity<TabsPresenter>() {
    private val weibo = arrayOf("精选", "推荐", "榜单", "故事", "综艺", "剧集", "VLOG", "明星", "电影")

    override fun getLayoutID(): Int {
        return R.layout.activity_tabs
    }

    override fun initView() {
        viewpager.adapter = object : BasePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return TabFragment.newInstance(weibo[position])
            }

            override fun getCount(): Int {
                return weibo.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return weibo[position]
            }

            override fun getPageIcon(position: Int): Int {
                return R.drawable.ic_switch_camera_black_24dp
            }
        }
        //GlueTabLayout 设置下划线指示器圆角
        tab_layout.setSelectedTabIndicator(R.drawable.tab_selected_bg)
        tab_layout.setSelectedTabIndicatorGravity(GlueTabLayout.INDICATOR_GRAVITY_STRETCH)
        tab_layout.tabMode = GlueTabLayout.MODE_SCROLLABLE

        //GlueTabLayout 设置下划线指示器的宽度为原来的一半
        tab_layout.setTabIndicatorWidth(0.8f)
        tab_layout.setupWithViewPager(viewpager)
//        for (index:Int in weibo.indices){
//            tab_layout.getTabAt(index)!!.setIcon(R.drawable.ic_switch_camera_black_24dp)
//        }
    }

    override fun initPresenter(): TabsPresenter {
        return TabsPresenter()
    }
}