package com.silverknife.meizhi.features.feature_main

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.navigation.NavigationView
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.ICallbacklInterface
import com.silverknife.meizhi.IManagerInterface
import com.silverknife.meizhi.R
import com.silverknife.meizhi.features.feature_gank.GankFragment
import com.silverknife.meizhi.features.feature_news.NewsFragment
import com.silverknife.meizhi.features.feature_test.TestFragment
import com.silverknife.meizhi.features.feature_xiandu.XianduFragment
import com.silverknife.meizhi.services.CustomService
import com.silverknife.meizhi.utils.JNITools
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainPresenter>(), IMainView {
    override fun getLayoutID(): Int = R.layout.activity_main
    var iManagerInterface: IManagerInterface? = null
    var currentFramgnet: Fragment? = null
    var fragments: ArrayList<Fragment> = arrayListOf()
    override fun initView() {
        fragments.add(NewsFragment())
        fragments.add(GankFragment.newInstance())
        fragments.add(TestFragment.newInstance())
        fragments.add(XianduFragment())
        showFragment(fragments[2])
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
        mainHandler = Handler() { msg: Message? ->
            Log.d("debug", "${Thread.currentThread()}")
            true
        }
        mainHandler?.sendEmptyMessage(0)
        bindCustomService()
    }

    fun bindCustomService() {
        var service = Intent(this, CustomService::class.java)
        bindService(service, object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
                unbindService(this)
                Log.d("debug", "onServiceDisconnected")
                Log.d("debug", "${name}")
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d("debug", "${name}")
                iManagerInterface = IManagerInterface.Stub.asInterface(service)
                try {
                    Log.d("debug", "${service}")
                    iManagerInterface?.setCallBack(object : ICallbacklInterface.Stub() {
                        override fun callback() {
                            Log.d("debug", "aidlcallback")
                        }
                    })
                    iManagerInterface?.test()
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
                Log.d("debug", "onServiceConnected")

            }
        }, Context.BIND_AUTO_CREATE)
    }

    override fun onBackPressed() {
        if (currentFramgnet != fragments[2]) {
            showFragment(fragments[2])
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

    companion object {
        var mainHandler: Handler? = null
    }
}
