package com.silverknife.meizhi.mvp.ui.fragment

import android.os.Bundle
import android.view.View
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.TabPresenter
import kotlinx.android.synthetic.main.tab_tmp.*

class TabFragment : BaseFragment<TabPresenter>() {
    private var mParam1: String? = null

    companion object {
        @JvmStatic
        private val ARG_PARAM1 = "param1"

        @JvmStatic
        fun newInstance(param1: String): TabFragment {
            val fragment = TabFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.tab_tmp
    }

    override fun initView(view: View) {
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
        }
        textView.text = mParam1
    }

    override fun initPresenter(): TabPresenter {
        return TabPresenter()
    }
}