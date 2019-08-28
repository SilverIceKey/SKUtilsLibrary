package com.silverknife.meizhi

import android.Manifest
import androidx.camera.core.*
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silvericekey.skutilslibrary.utils.CameraUtil
import com.silvericekey.skutilslibrary.utils.PermissionUtil
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : BaseActivity<CameraPresenter>() {
    override fun initView() {
        permissionCheck()
    }

    private fun permissionCheck() {
        if (PermissionUtil.hasPermission(Manifest.permission.CAMERA)) {
            initCamera()
        } else {
            requestPermission(permissions = *arrayOf(Manifest.permission.CAMERA), callback = object : PermissionUtil.PermissionSimpleCallback {
                override fun permissionGranted() {
                    initCamera()
                }

                override fun permissionDenied() {

                }

            })
        }
    }

    private fun initCamera() {
        var cameraUtil = CameraUtil(this)
        cameraUtil.setTextureView(view_finder)
        cameraUtil.setLifecyclerOwner(this)
            cameraUtil.setCameraId(CameraX.LensFacing.BACK)
        view_finder.post { cameraUtil.startCamera() }
        view_finder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            cameraUtil.updateTransform(view_finder)
        }
    }

    override fun initPresenter(): CameraPresenter = CameraPresenter()

    override fun getLayoutID(): Int = R.layout.activity_camera

}
