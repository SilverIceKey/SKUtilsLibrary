package com.silverknife.meizhi.mvp.ui.activity

import android.view.View
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.SizeUtils
import com.negier.gluetablayout.BasePagerAdapter
import com.negier.gluetablayout.GlueTabLayout
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.TabsPresenter
import com.silverknife.meizhi.mvp.ui.fragment.TabFragment
import kotlinx.android.synthetic.main.activity_tabs.*
import kotlinx.android.synthetic.main.tab_custom_view.view.*

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

            override fun getPageCustomView(position: Int): View {
                var custom = layoutInflater.inflate(R.layout.tab_custom_view, null)
                custom.text.text = weibo[position]
                custom.num.text = if ((position * 10).toString().equals("0")) "" else (position * 10).toString()
                var layoutParams = custom.num.layoutParams as RelativeLayout.LayoutParams
                if ((position * 10).toString().equals("0")) {
                    layoutParams.width = SizeUtils.dp2px(10f)
                    layoutParams.height = SizeUtils.dp2px(10f)
                }else{
                    layoutParams.width = RelativeLayout.LayoutParams.WRAP_CONTENT
                    layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT
                }
                layoutParams.leftMargin = custom.icon.width - SizeUtils.dp2px(5f)
                custom.num.layoutParams = layoutParams
                return custom
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