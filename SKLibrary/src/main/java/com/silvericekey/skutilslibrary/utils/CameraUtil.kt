package com.silvericekey.skutilslibrary.utils

import android.content.Context
import android.graphics.Matrix
import android.util.Rational
import android.util.Size
import android.view.Surface
import android.view.TextureView
import androidx.camera.core.*
import androidx.lifecycle.LifecycleOwner
import com.silvericekey.skutilslibrary.camera.AutoFitPreviewBuilder
import com.silvericekey.skutilslibrary.camera.PreviewAnalyzer

class CameraUtil {
    var context: Context? = null
    var useCases = arrayOf<UseCase>()

    constructor(context: Context) {
        this.context = context
    }

    private var view_finder: TextureView? = null
    fun setTextureView(view_finder: TextureView): CameraUtil {
        this.view_finder = view_finder
        return this
    }

    private var cameraId: CameraX.LensFacing = CameraX.LensFacing.BACK;
    fun setCameraId(cameraId: CameraX.LensFacing): CameraUtil {
        this.cameraId = cameraId
        return this
    }

    private var lifecycleOwner: LifecycleOwner? = null
    fun setLifecyclerOwner(lifecycleOwner: LifecycleOwner): CameraUtil {
        this.lifecycleOwner = lifecycleOwner
        return this
    }

    fun addUseCase(useCase: UseCase): CameraUtil {
        useCases.plus(useCase)
        return this
    }

    fun startCamera() {
        if (lifecycleOwner == null || view_finder == null) {
            throw NullPointerException("请先设置lifecycleOwner和view_finder")
        }
        startCamera(lifecycleOwner!!, view_finder!!)
    }

    fun startCamera(lifecycleOwner: LifecycleOwner, view_finder: TextureView) {
        // Create configuration object for the viewfinder use case
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetAspectRatio(Rational(1, 1))
            setTargetResolution(Size(view_finder.measuredWidth, view_finder.measuredHeight))
            setLensFacing(cameraId)
        }.build()


        // Setup image analysis pipeline that computes average pixel luminance
        val analyzerConfig = ImageAnalysisConfig.Builder().build()

        // Build the image analysis use case and instantiate our analyzer
        val analyzerUseCase = ImageAnalysis(analyzerConfig).apply {
            analyzer = PreviewAnalyzer()
        }

        // Build the viewfinder use case
        val preview = AutoFitPreviewBuilder.build(previewConfig, view_finder)

        // Every time the viewfinder is updated, recompute layout
//        preview.setOnPreviewOutputUpdateListener {
//
//            // To update the SurfaceTexture, we have to remove it and re-add it
//            val parent = view_finder.parent as ViewGroup
//            parent.removeView(view_finder)
//            parent.addView(view_finder, 0)
//
//            view_finder.surfaceTexture = it.surfaceTexture
//            updateTransform(view_finder)
//        }

        // Bind use cases to lifecycle
        // If Android Studio complains about "this" being not a LifecycleOwner
        // try rebuilding the project or updating the appcompat dependency to
        // version 1.1.0 or higher.
        useCases.plus(analyzerUseCase)
        CameraX.bindToLifecycle(lifecycleOwner, preview, *useCases)
    }

    fun updateTransform(view_finder: TextureView) {
        val matrix = Matrix()

        // Compute the center of the view finder
        val centerX = view_finder.width / 2f
        val centerY = view_finder.height / 2f

        // Correct preview output to account for display rotation
        val rotationDegrees = when (view_finder.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        // Finally, apply transformations to our TextureView
        view_finder.setTransform(matrix)
    }
}