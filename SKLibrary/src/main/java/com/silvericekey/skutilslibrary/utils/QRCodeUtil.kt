package com.silvericekey.skutilslibrary.utils

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.BinaryBitmap
import com.google.zxing.LuminanceSource
import com.google.zxing.RGBLuminanceSource
import com.google.zxing.common.BitMatrix
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.QRCodeReader
import com.google.zxing.qrcode.QRCodeWriter


object QRCodeUtil {
    fun createQRCode(info: String, size: Int): Bitmap = createQRCode(info, size, -1)
    fun createQRCode(info: String, size: Int, color: Int): Bitmap = bitMatrixToBitmap(QRCodeWriter().encode(String(info.toByteArray(Charsets.UTF_8), Charsets.ISO_8859_1), BarcodeFormat.QR_CODE, size, size), color)

    fun readQRCode(bitmap: Bitmap):String{
        var pixels = intArrayOf()
        bitmap.getPixels(pixels,0,bitmap.width,0,0,bitmap.width,bitmap.height)
        var luminanceSource = RGBLuminanceSource(bitmap.width,bitmap.height,pixels)
        var binaryBitmap = BinaryBitmap(HybridBinarizer(luminanceSource))
        return QRCodeReader().decode(binaryBitmap).text
    }

    private fun bitMatrixToBitmap(bitMatrix: BitMatrix, color: Int): Bitmap {
        val width = bitMatrix.width
        val height = bitMatrix.height

        val pixels = IntArray(width * height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                pixels[y * width + x] = if (bitMatrix.get(x, y)) {
                    if (color == -1 || color == Color.parseColor("#ffffff")) -0x1000000 else color
                } else -0x1
            }
        }
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height)

        return bitmap
    }
}