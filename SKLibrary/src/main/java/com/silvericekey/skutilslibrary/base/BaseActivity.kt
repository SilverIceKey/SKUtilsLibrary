package com.silvericekey.skutilslibrary.base

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import com.silvericekey.skutilslibrary.utils.PermissionUtil
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseActivity<T : BasePresenter> : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    protected lateinit var mPresenter: T

    var optionsCompat: ActivityOptionsCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            this.intent.putExtras(savedInstanceState)
        }
        setContentView(getLayoutID())
        initStatusBar()
        mPresenter = initPresenter()
        for (view in views.keys) {
            ViewCompat.setTransitionName(view, views[view])
        }
        initView()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.putAll(this.intent.extras)
        saveDataWhenKill(savedInstanceState)
    }

    override fun onRestart() {
        super.onRestart()
        mPresenter.refreshData()
    }

    abstract fun getLayoutID(): Int

    abstract fun initView()

    abstract fun initPresenter(): T

    fun saveDataWhenKill(outState: Bundle?) {

    }

    fun initStatusBar() {
        BaseApplication.getApp().statusChange()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    var simpleCallback: PermissionUtil.PermissionSimpleCallback? = null;
    var fullCallback: PermissionUtil.PermissionFullCallback? = null;
    fun requestPermission(vararg permissions: String, callback: PermissionUtil.PermissionSimpleCallback) {
        simpleCallback = callback
        EasyPermissions.requestPermissions(this, "", PermissionUtil.PERMISSION_REQUEST, *permissions)
    }

    fun requestPermission(vararg permissions: String, callback: PermissionUtil.PermissionFullCallback) {
        fullCallback = callback
        EasyPermissions.requestPermissions(this, "", PermissionUtil.PERMISSION_REQUEST, *permissions)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        simpleCallback?.permissionGranted()
        fullCallback?.permissionGranted(perms)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        simpleCallback?.permissionDenied()
        fullCallback?.permissionDenied(perms)
    }

    var views: HashMap<View, String> = hashMapOf()
    fun addTransitionName(view: View, tag: String) {
        views.put(view, tag)
    }

    fun initOptionsCompat(vararg sharedElements: Pair<View, String>) {
        optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *sharedElements)
    }

    override fun startActivity(intent: Intent?) {
        if (optionsCompat == null) {
            super.startActivity(intent)
        } else {
            startActivity(intent, optionsCompat?.toBundle())
        }
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }
}