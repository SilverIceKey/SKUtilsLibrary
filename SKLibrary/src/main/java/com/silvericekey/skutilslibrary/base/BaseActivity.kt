package com.silvericekey.skutilslibrary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.silvericekey.skutilslibrary.utils.PermissionUtil
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseActivity<T : BasePresenter> : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    protected lateinit var mPresenter: T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            this.intent.putExtras(savedInstanceState)
        }
        setContentView(getLayoutID())
        initStatusBar()
        mPresenter = initPresenter()
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

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }
}