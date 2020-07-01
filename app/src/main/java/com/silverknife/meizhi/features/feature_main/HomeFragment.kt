package com.silverknife.meizhi.features.feature_main

import android.Manifest
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.PermissionUtils
import com.negier.gluetablayout.BasePagerAdapter
import com.negier.gluetablayout.GlueTabLayout
import com.silvericekey.skutilslibrary.base.BaseFragment
import com.silverknife.meizhi.R
import com.silverknife.meizhi.features.feature_gank.GankFragment
import com.silverknife.meizhi.features.feature_test.TabFragment
import com.silverknife.meizhi.features.feature_test.TestFragment
import kotlinx.android.synthetic.main.activity_tabs.*

class HomeFragment : BaseFragment<HomePresenter>() {
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
    }

    override fun initPresenter(): HomePresenter {
        return HomePresenter()
    }
}