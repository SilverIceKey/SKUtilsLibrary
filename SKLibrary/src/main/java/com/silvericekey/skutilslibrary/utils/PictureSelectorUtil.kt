package com.silvericekey.skutilslibrary.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.ToastUtils

class PictureSelectorUtil {
    companion object{
        @JvmStatic
        private var pictureSelectorUtil:PictureSelectorUtil? = null
        @JvmStatic
        fun  getInstance():PictureSelectorUtil{
            if (pictureSelectorUtil==null){
                synchronized(PictureSelectorUtil::class.java,{
                    if (pictureSelectorUtil==null){
                        pictureSelectorUtil = PictureSelectorUtil()
                    }
                })
            }
            return pictureSelectorUtil!!
        }
    }
    /**
     * 直接调用系统相机拍照
     * **/
    fun getPictureWithCamera(context:Context) {
        if (getPermission({
                    ToastUtils.showShort("您拒绝了开启权限，改功能无法使用")
                }, {
                    getPictureWithCamera(context)
                })) {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            context.startActivity(intent)
        }
    }
    /*
    * 调用系统相册
    * */
    fun getPictureWithGallery(context: Context) {
        if (getPermission({
                    ToastUtils.showShort("您拒绝了开启权限，改功能无法使用")
                }, {
                    getPictureWithGallery(context)
                })) {
            var intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*");

            context.startActivity(intent)
        }
    }

    private fun getPermission(onGranted: () -> Unit, onDenied: () -> Unit): Boolean {
        if (!PermissionUtils.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)) {
            PermissionUtils.permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                    .callback(object : PermissionUtils.SimpleCallback {
                        override fun onGranted() {
                            onGranted()
                        }

                        override fun onDenied() {
                            onDenied()
                        }

                    }).request()
            return false
        } else {
            return true
        }
    }
}