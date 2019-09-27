package com.silverknife.meizhi.mvp.ui.activity

import android.Manifest
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.SizeUtils
import com.negier.gluetablayout.BasePagerAdapter
import com.negier.gluetablayout.GlueTabLayout
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.MainPresenter
import com.silverknife.meizhi.mvp.ui.fragment.GankFragment
import com.silverknife.meizhi.mvp.ui.fragment.TabFragment
import com.silverknife.meizhi.mvp.ui.fragment.TestFragment
import com.silverknife.meizhi.mvp.ui.interfaces.IMainView
import kotlinx.android.synthetic.main.activity_tabs.*
import kotlinx.android.synthetic.main.tab_custom_view.view.*

class MainActivity : BaseActivity<MainPresenter>(), IMainView {
    private val index = arrayOf("干货", "测试")
    override fun getLayoutID(): Int = R.layout.activity_main

    override fun initView() {
        if (!PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            PermissionUtils.permission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .callback(object :PermissionUtils.SimpleCallback{
                        override fun onGranted() {
                            loadData()
                        }

                        override fun onDenied() {

                        }
                    }).request()
        }else{
            loadData()
        }
    }

    fun loadData(){
        viewpager.adapter = object : BasePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                when(position){
                    0->return GankFragment.newInstance()
                    1->return TestFragment.newInstance()
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
        tab_layout.setTabTextColors(ContextCompat.getColor(MainActivity@this,R.color.tab_normal),ContextCompat.getColor(MainActivity@this,R.color.tab_selected))
        //GlueTabLayout 设置下划线指示器的宽度为原来的一半
        tab_layout.setTabIndicatorWidth(0.8f)
        tab_layout.setupWithViewPager(viewpager)
    }

    override fun initPresenter(): MainPresenter = MainPresenter()
}
