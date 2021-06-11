package com.silvericekey.skutilslibrary.utils.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Base64;

/**
 * 图像处理相关
 */
public class ImageHelper {


    /**
     * 获取亮度调整后的图片
     *
     * @param bmp base64
     * @param lum
     * @return
     */
    public static Bitmap getNewBitmap(String bmp, float lum) {
        return getNewBitmap(base64ToBitmap(bmp),lum);
    }

    /**
     * 获取亮度调整后的图片
     *
     * @param bmp
     * @param lum
     * @return
     */
    public static Bitmap getNewBitmap(Bitmap bmp, float lum) {
        Bitmap bitmap = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);

        //调整亮度
        ColorMatrix lueMatrix = new ColorMatrix();
        lueMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix allMatrix = new ColorMatrix();
        allMatrix.postConcat(lueMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(allMatrix));
        canvas.drawBitmap(bmp, 0, 0, paint);

        return bitmap;
    }

    /*
     * base64转bitmap
     * */
    public static Bitmap base64ToBitmap(String base64) {
        byte[] decode = Base64.decode(base64, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        return bitmap;
    }
}
