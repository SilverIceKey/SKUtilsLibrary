package com.silverknife.meizhi.features.feature_test

import android.os.Bundle
import android.view.View
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silverknife.meizhi.R
import kotlinx.android.synthetic.main.tab_tmp.*
import me.jessyan.autosize.utils.LogUtils

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

    override fun onDestroyView() {
        LogUtils.e("onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        LogUtils.e("onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        LogUtils.e("onDetach")
        super.onDetach()
    }
}