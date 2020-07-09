package com.silverknife.meizhi.features.feature_main

import android.graphics.Color
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.customview.widget.ViewDragHelper
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.navigation.NavigationView
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.features.feature_gank.GankFragment
import com.silverknife.meizhi.features.feature_news.NewsFragment
import com.silverknife.meizhi.features.feature_test.TestFragment
import com.silverknife.meizhi.features.feature_test.XianduFragment
import com.silverknife.meizhi.utils.JNITools
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), IMainView {
    override fun getLayoutID(): Int = R.layout.activity_main
    var currentFramgnet: Fragment? = null
    var fragments: ArrayList<Fragment> = arrayListOf()
    override fun initView() {
        fragments.add(NewsFragment())
        fragments.add(GankFragment.newInstance())
        fragments.add(TestFragment.newInstance())
        fragments.add(XianduFragment())
        showFragment(fragments[0])
        navigation_header_container.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(p0: MenuItem): Boolean {
                when (p0.itemId) {
                    R.id.home -> showFragment(fragments[0])
                    R.id.gank -> showFragment(fragments[1])
                    R.id.test -> showFragment(fragments[2])
                    R.id.xiandu -> showFragment(fragments[3])
                }
                return false
            }
        })
        ToastUtils.showShort(JNITools().getApi())
    }

    override fun onBackPressed() {
        if (currentFramgnet != fragments[0]) {
            showFragment(fragments[0])
            return
        }
        super.onBackPressed()
    }

    fun showFragment(fragment: Fragment) {
        if (!fragment.isAdded) {
            if (currentFramgnet == null) {
                supportFragmentManager.commitNow { add(R.id.content, fragment, fragment::class.java.simpleName) }
            } else {
                supportFragmentManager.commitNow { hide(currentFramgnet!!).add(R.id.content, fragment, fragment::class.java.simpleName) }
            }
        } else {
            supportFragmentManager.commitNow { hide(currentFramgnet!!).show(fragment) }
        }
        currentFramgnet = fragment
        drawer_layout.closeDrawer(navigation_header_container)

    }

    override fun initPresenter(): MainPresenter = MainPresenter()
}
