package com.silverknife.meizhi.mvp.ui.fragment

import android.Manifest
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.PermissionUtils
import com.negier.gluetablayout.BasePagerAdapter
import com.negier.gluetablayout.GlueTabLayout
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.activity_tabs.*

class HomeFragment : BaseFragment<HomePresenter>() {
    private val index = arrayOf("干货", "测试")
    override fun getLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun initView(view: View) {
        if (!PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PermissionUtils.permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .callback(object : PermissionUtils.SimpleCallback {
                        override fun onGranted() {
                            loadData()
                        }

                        override fun onDenied() {

                        }
                    }).request()
        } else {
            loadData()
        }
    }

    fun loadData() {
        viewpager.adapter = object : BasePagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                when (position) {
                    0 -> return GankFragment.newInstance()
                    1 -> return TestFragment.newInstance()
                }
                return TabFragment.newInstance(index[position])
            }

            override fun getCount(): Int {
                return index.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return index[position]
            }
        }
        //GlueTabLayout 设置下划线指示器圆角
        tab_layout.tabMode = GlueTabLayout.MODE_FIXED
        tab_layout.setTabTextColors(ContextCompat.getColor(activity!!, R.color.tab_normal), ContextCompat.getColor(activity!!, R.color.tab_selected))
        //GlueTabLayout 设置下划线指示器的宽度为原来的一半
        tab_layout.setTabIndicatorWidth(0.8f)
        tab_layout.setupWithViewPager(viewpager)
    }

    override fun initPresenter(): HomePresenter {
        return HomePresenter()
    }
}