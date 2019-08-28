package com.silvericekey.skutilslibrary.utils

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import com.google.zxing.common.BitMatrix



object QRCodeUtil {
    fun createQRCode(info: String, size: Int): Bitmap = bitMatrixToBitmap(QRCodeWriter().encode(info, BarcodeFormat.QR_CODE, size, size))

    private fun bitMatrixToBitmap(bitMatrix: BitMatrix): Bitmap {
        val width = bitMatrix.width
        val height = bitMatrix.height

        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                pixels[y * width + x] = if (bitMatrix.get(x, y)) -0x1000000 else -0x1
            }
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)

        return bitmap
    }
}