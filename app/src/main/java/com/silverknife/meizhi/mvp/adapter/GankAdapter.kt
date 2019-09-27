package com.silverknife.meizhi.mvp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.negier.gluetablayout.BasePagerAdapter

class GankAdapter(fm: FragmentManager?) : BasePagerAdapter(fm) {
    var pageTitles: ArrayList<String> = arrayListOf()
    var pages: ArrayList<Any> = arrayListOf()
    override fun getItem(position: Int): Fragment {
        return pages.get(position) as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageTitles[position]
    }
    override fun getCount(): Int {
        return pageTitles.size
    }
}