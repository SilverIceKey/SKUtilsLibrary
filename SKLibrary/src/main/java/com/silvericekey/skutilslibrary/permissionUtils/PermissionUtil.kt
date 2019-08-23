package com.silvericekey.skutilslibrary.permissionUtils

import android.Manifest
import com.silvericekey.skutilslibrary.SKUtilsLibrary
import pub.devrel.easypermissions.EasyPermissions

object PermissionUtil {
    val PERMISSION_REQUEST = 100
    fun hasPermission(vararg permissions:String): Boolean {
        return EasyPermissions.hasPermissions(SKUtilsLibrary.context!!, *permissions)
    }

    interface PermissionSimpleCallback{
        fun permissionGranted()
        fun permissionDenied()
    }
    interface PermissionFullCallback{
        fun permissionGranted(permsGranted: MutableList<String>)
        fun permissionDenied(permsDenied: MutableList<String>)
    }
}