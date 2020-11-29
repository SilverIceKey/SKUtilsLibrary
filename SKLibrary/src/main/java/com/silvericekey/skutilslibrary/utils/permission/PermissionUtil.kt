package com.silvericekey.skutilslibrary.utils.permission

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.ActivityUtils
import com.silvericekey.skutilslibrary.base.BaseApplication
import pub.devrel.easypermissions.EasyPermissions

class PermissionUtil {
    companion object{
        @JvmStatic
        val PERMISSION_REQUEST = 100
        @JvmStatic
        private var permissionUtil:PermissionUtil? = null
        @JvmStatic
        fun  getInstance():PermissionUtil{
            if (permissionUtil==null){
                synchronized(PermissionUtil::class.java,{
                    if (permissionUtil==null){
                        permissionUtil = PermissionUtil()
                    }
                })
            }
            return permissionUtil!!
        }
    }
    fun hasPermission(vararg permissions: String): Boolean {
        return EasyPermissions.hasPermissions(BaseApplication.getApp(), *permissions)
    }

    fun canInstallAPK() {
        var haveInstallPermission: Boolean
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先判断是否有安装未知来源应用的权限
            haveInstallPermission = BaseApplication.getApp().getPackageManager()!!.canRequestPackageInstalls();
            if (!haveInstallPermission) {
                //弹框提示用户手动打开
                AlertDialog.Builder(ActivityUtils.getTopActivity()!!)
                        .setTitle("安装权限")
                        .setMessage("需要打开允许来自此来源，请去设置中开启此权限")
                        .setPositiveButton("确定", object:DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                toInstallPermissionSettingIntent()
                                dialog!!.dismiss()
                            }

                        }).show()
                return
            }
        }
    }
    var INSTALL_PERMISS_CODE = 100
    @RequiresApi(api = Build.VERSION_CODES.O)
    fun toInstallPermissionSettingIntent() {
        var packageURI = Uri.parse ("package:" + BaseApplication.getApp().getPackageName())
        var intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI)
        ActivityUtils.startActivityForResult(ActivityUtils.getTopActivity(),intent, INSTALL_PERMISS_CODE)
    }

    interface PermissionSimpleCallback {
        fun permissionGranted()
        fun permissionDenied()
    }

    interface PermissionFullCallback {
        fun permissionGranted(permsGranted: MutableList<String>)
        fun permissionDenied(permsDenied: MutableList<String>)
    }
}