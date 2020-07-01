package com.silverknife.meizhi.features.feature_gank

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.negier.gluetablayout.GlueTabLayout
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silverknife.meizhi.R
import kotlinx.android.synthetic.main.fragment_gank.*

class GankFragment : BaseFragment<GankPresenter>() {
    companion object {
        @JvmStatic
        fun newInstance(): GankFragment {
            val fragment = GankFragment()
            val args = Bundle()
            fragment.setArguments(args)
            return fragment
        }
    }

    var gankAdapter: GankAdapter? = null

    override fun getLayoutID(): Int {
        return R.layout.fragment_gank
    }

    override fun initView(view: View) {
        gankAdapter = GankAdapter(childFragmentManager)
        mPresenter?.getData {
            val gankResponse = it
            gankAdapter!!.pageTitles.addAll(gankResponse.category!!)
            gankResponse.category!!.forEach {
                gankAdapter!!.pages.add(GankListFragment.newInstance(gankResponse.results!![it]!!))
            }
            viewpager.adapter = gankAdapter
            tab_layout.tabMode = GlueTabLayout.MODE_SCROLLABLE
            tab_layout.setTabTextColors(ContextCompat.getColor(context!!,R.color.tab_normal), ContextCompat.getColor(context!!,R.color.tab_selected))
            //GlueTabLayout 设置下划线指示器的宽度为原来的一半
            tab_layout.setTabIndicatorWidth(0.8f)
            tab_layout.setupWithViewPager(viewpager)
        }
    }

    override fun initPresenter(): GankPresenter {
        return GankPresenter()
    }
}