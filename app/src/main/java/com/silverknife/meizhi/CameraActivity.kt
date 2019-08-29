package com.silverknife.meizhi

import android.Manifest
import android.view.View
import androidx.camera.core.CameraX
import com.silvericekey.skutilslibrary.base.BaseActivity
import com.silvericekey.skutilslibrary.utils.CameraUtil
import com.silvericekey.skutilslibrary.utils.PermissionUtil
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : BaseActivity<CameraPresenter>() {
    var cameraUtil: CameraUtil? = null
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
        cameraUtil = CameraUtil(this)
        cameraUtil?.setTextureView(view_finder)
        cameraUtil?.setLifecyclerOwner(this)
        cameraUtil?.setCameraId(CameraX.LensFacing.FRONT)
        cameraUtil?.addUseCase({
            println("add usecase")
        })
        view_finder.post { cameraUtil?.startCamera() }
        view_finder.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            cameraUtil?.updateTransform(view_finder)
        }
    }

    fun changeCamera(view: View) {
        cameraUtil?.changeCamera()
    }

    override fun initPresenter(): CameraPresenter = CameraPresenter()

    override fun getLayoutID(): Int = R.layout.activity_camera

}
