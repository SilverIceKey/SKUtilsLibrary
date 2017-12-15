package com.silvericekey.skutilslibrary;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.picasso.Picasso;

/**
 * Created by silverknife on 2017/12/13.
 */

public class SKUtilsLibrary {
    private static Context mContext;

    public static void init(Context context){
        mContext = context;
        Fresco.initialize(context);
    }

    public static Context getContext() {
        if (mContext==null){
            throw new NullPointerException("Please init SKUtilsLibrary in your Application");
        }
        return mContext;
    }
}
