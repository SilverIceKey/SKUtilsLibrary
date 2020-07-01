package com.silverknife.meizhi.features.feature_test

import android.view.View
import com.facebook.react.LifecycleState
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler
import com.facebook.react.shell.MainReactPackage
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silverknife.meizhi.BuildConfig


class RNActivity : BaseActivity<RNPresenter>(), DefaultHardwareBackBtnHandler {
    lateinit var mReactRootView: ReactRootView
    lateinit var mReactInstanceManager: ReactInstanceManager
    override fun invokeDefaultOnBackPressed() {
        super.onBackPressed()
    }

    override fun getLayout(): View? {
        mReactRootView = ReactRootView(this)
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(application)
                .setBundleAssetName("index.android.bundle")
                .setJSBundleFile("index.android")
                .addPackage(MainReactPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build()
        //这里的AndroidRnDemoApp必须对应“index.js”中的“AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, "AndroidRnDemoApp", null)
        //加载ReactRootView到布局中
        return mReactRootView
    }

    override fun onPause() {
        super.onPause()
        mReactInstanceManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        mReactInstanceManager.onResume(this,this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mReactInstanceManager.onDestroy()
    }




    override fun initView() {

    }

    override fun initPresenter(): RNPresenter = RNPresenter()
}