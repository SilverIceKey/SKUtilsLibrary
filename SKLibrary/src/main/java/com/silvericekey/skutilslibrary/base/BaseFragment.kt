package com.silvericekey.skutilslibrary.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import com.silvericekey.skutilslibrary.utils.PermissionUtil
import com.silvericekey.skutilslibrary.utils.SystemBarUtil
import pub.devrel.easypermissions.EasyPermissions

abstract class BaseFragment<T : BasePresenter> : Fragment(), EasyPermissions.PermissionCallbacks, IBaseFragment {
    protected lateinit var mPresenter: T

    var optionsCompat: ActivityOptionsCompat? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPresenter = initPresenter()
        var view = LayoutInflater.from(context).inflate(getLayoutID(), null)
        if (fitSystemBar()) {
            SystemBarUtil.setPadding(context, view)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    abstract fun getLayoutID(): Int

    abstract fun initView(view: View)

    abstract fun initPresenter(): T

    open fun onVisibale() {

    }

    open fun fitSystemBar(): Boolean {
        return false
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (mPresenter != null && isVisibleToUser) {
            onVisibale()
        }
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

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    fun initOptionsCompat(vararg sharedElements: Pair<View, String>) {
        optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, *sharedElements)
    }

    override fun startActivity(intent: Intent?) {
        if (optionsCompat == null) {
            super.startActivity(intent)
        } else {
            startActivity(intent, optionsCompat?.toBundle())
        }
    }

    override fun getFragment(): Fragment {
        return this
    }

    override fun onDestroy() {
        mPresenter?.onDestroy()
        super.onDestroy()
    }
}