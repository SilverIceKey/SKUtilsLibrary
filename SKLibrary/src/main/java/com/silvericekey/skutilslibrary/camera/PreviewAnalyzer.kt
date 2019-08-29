package com.silvericekey.skutilslibrary.camera

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import android.graphics.BitmapFactory
import android.graphics.Bitmap



class PreviewAnalyzer:ImageAnalysis.Analyzer {
    override fun analyze(image: ImageProxy?, rotationDegrees: Int) {
        val buffer = image!!.getPlanes()[0].buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        val myBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size, null)
        println("bitmap--->"+myBitmap.toString())
    }
}