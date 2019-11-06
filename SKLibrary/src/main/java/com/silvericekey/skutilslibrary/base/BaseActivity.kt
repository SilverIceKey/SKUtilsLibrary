package com.silvericekey.skutilslibrary.base

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import com.flyco.systembar.SystemBarHelper
import com.silvericekey.skutilslibrary.utils.PermissionUtil
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseActivity<T : BasePresenter> : AppCompatActivity(), EasyPermissions.PermissionCallbacks,IBaseView {
    protected lateinit var mPresenter: T

    var optionsCompat: ActivityOptionsCompat? = null
    var slideToFinishSpeed = 2
    var openSlideToFinish = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            this.intent.putExtras(savedInstanceState)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            var flag: Int
            if (isStatusDark()) {
                flag = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            } else {
                flag = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            }
            window.decorView.systemUiVisibility = flag//导航栏不会被隐藏但activity布局会扩展到导航栏所在位置
            window.navigationBarColor = Color.TRANSPARENT
            window.statusBarColor = statusColor()
        }
        setContentView(getLayoutID())
        SystemBarHelper.setPadding(this, window.decorView.findViewById(android.R.id.content))
        initStatusBar()
        mPresenter = initPresenter()
        initTransitionViews()
        for (view in views.keys) {
            ViewCompat.setTransitionName(view, views[view])
        }
        initView()
    }

    open fun isStatusDark(): Boolean {
        return true
    }

    open fun statusColor(): Int {
        return Color.WHITE
    }

    open fun initTransitionViews() {

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

    private var mDownX: Int = 0
    private var mDownTime: Long = 0
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev!!.action) {
            MotionEvent.ACTION_DOWN -> {
                mDownX = ev.x.toInt()
                mDownTime = System.currentTimeMillis()
            }
            MotionEvent.ACTION_UP -> {
                if ((ev.x.toInt() - mDownX) / (System.currentTimeMillis() - mDownTime) >= slideToFinishSpeed && openSlideToFinish) {
                    finish()
                    return true
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }
}