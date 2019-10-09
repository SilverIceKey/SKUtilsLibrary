package com.silverknife.meizhi.mvp.ui.activity

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.R
import com.silverknife.meizhi.mvp.presenter.MainPresenter
import com.silverknife.meizhi.mvp.ui.fragment.HomeFragment
import com.silverknife.meizhi.mvp.ui.fragment.XianduFragment
import com.silverknife.meizhi.mvp.ui.interfaces.IMainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), IMainView {
    override fun getLayoutID(): Int = R.layout.activity_main
    var currentFramgnet: Fragment? = null
    var fragments: ArrayList<Fragment> = arrayListOf()
    var transaction: FragmentTransaction? = null
    override fun initView() {
        transaction = supportFragmentManager.beginTransaction()
        fragments.add(HomeFragment())
        fragments.add(XianduFragment())
        showFragment(fragments[0])
        navigation_header_container.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(p0: MenuItem): Boolean {
                when (p0.itemId) {
                    R.id.home -> showFragment(fragments[0])
                    R.id.xiandu -> showFragment(fragments[1])
                }
                return false
            }
        })
    }

    override fun onBackPressed() {
        if (currentFramgnet!=fragments[0]){
            showFragment(fragments[0])
            return
        }
        super.onBackPressed()
    }

    fun showFragment(fragment: Fragment) {
        if (!fragment.isAdded) {
            if (currentFramgnet == null) {
                supportFragmentManager.beginTransaction().add(R.id.content, fragment, fragment::class.java.simpleName).commit()
            } else {
                supportFragmentManager.beginTransaction().hide(currentFramgnet!!).add(R.id.content, fragment, fragment::class.java.simpleName).commit()
            }
        } else {
            supportFragmentManager.beginTransaction().hide(currentFramgnet!!).show(fragment).commit()
        }
        currentFramgnet = fragment
        drawer_layout.closeDrawer(navigation_header_container)

    }

    override fun initPresenter(): MainPresenter = MainPresenter()
}
