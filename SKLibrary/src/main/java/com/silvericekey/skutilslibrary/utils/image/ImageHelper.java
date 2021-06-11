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

    /**
     * 底片效果的图片
     */
    public static Bitmap getFilmPhoto(Bitmap bitmapSource) {
        int width = bitmapSource.getWidth();
        int height = bitmapSource.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] oldPix = new int[width * height];
        int[] newPix = new int[width * height];
        int color;
        int r, g, b, a;
        bitmapSource.getPixels(oldPix, 0, width, 0, 0, width, height);

        for (int i = 0; i < width * height; i++) {
            color = oldPix[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);


            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }

            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }


            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }
            newPix[i] = Color.argb(a, r, g, b);

        }
        bitmap.setPixels(newPix, 0, width, 0, 0, width, height);
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
