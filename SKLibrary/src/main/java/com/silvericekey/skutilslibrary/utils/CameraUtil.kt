package com.silvericekey.skutilslibrary.utils

import android.content.Context
import android.hardware.Camera
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.os.Build
import com.silvericekey.skutilslibrary.SKUtilsLibrary

/**
 * 摄像头工具类
 * */
object CameraUtil {
    /**
     * 是否打开摄像头
     * */
    fun enableFlashLight(enable:Boolean){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var cameraManager = SKUtilsLibrary.context!!.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            var ids = cameraManager.cameraIdList
            for (id in ids) {
                var c = cameraManager.getCameraCharacteristics(id)
                var flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
                var lensFacing = c.get(CameraCharacteristics.LENS_FACING)
                if (flashAvailable != null && flashAvailable && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                    cameraManager.setTorchMode(id, enable)
                }
            }
        } else {
            var camera = Camera.open()
            var paramets = camera.parameters
            if (paramets.flashMode == Camera.Parameters.FLASH_MODE_OFF) {
                paramets.flashMode = Camera.Parameters.FLASH_MODE_TORCH
            } else {
                paramets.flashMode = Camera.Parameters.FLASH_MODE_OFF
            }
            camera.parameters = paramets
            camera.release()
            camera = null
        }
    }
}