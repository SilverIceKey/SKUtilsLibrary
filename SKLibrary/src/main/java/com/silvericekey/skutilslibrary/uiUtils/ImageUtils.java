package com.silvericekey.skutilslibrary.uiUtils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.silvericekey.skutilslibrary.SKUtilsLibrary;


/**
 * Created by Administrator on 2017/5/24.
 */

public class ImageUtils {
    private static ImageUtils mInstance;

    public static ImageUtils getInstance() {
        if (mInstance == null) {
            synchronized (ImageUtils.class) {
                if (mInstance == null) {
                    mInstance = new ImageUtils();
                }
            }
        }
        return mInstance;
    }

    public static RequestBuilder bindImg(String url, RequestOptions options) {
        return bindImg(SKUtilsLibrary.getContext(),url,options);
    }

    public static RequestBuilder bindImg(Context context, String url, RequestOptions options) {
        RequestManager requestManager = Glide.with(context);
        return requestManager
                .load(url)
                .apply(options);

    }

    public static RequestOptions setRadius(RequestOptions option, int radius){
        option.transform(new RoundedCorners(radius));
        return option;
    }

    public static RequestOptions getOption(DiskCacheStrategy strategy, Priority priority, int placeholder, int fallback, int error) {
        RequestOptions option = new RequestOptions();
        option.diskCacheStrategy(strategy);
        option.priority(priority);
        option.placeholder(placeholder);
        option.fallback(fallback);
        option.error(error);
        return option;
    }

    public static RequestOptions getOption(DiskCacheStrategy strategy, Priority priority, Drawable placeholder, Drawable fallback, Drawable error) {
        RequestOptions option = new RequestOptions();
        option.diskCacheStrategy(strategy);
        option.priority(priority);
        option.placeholder(placeholder);
        option.fallback(fallback);
        option.error(error);
        return option;
    }

    public static RequestOptions getOption(DiskCacheStrategy strategy, Priority priority, Drawable drawable) {
        return getOption(strategy, priority, drawable, drawable, drawable);
    }

    public static RequestOptions getOption(DiskCacheStrategy strategy, Priority priority, int resourceId) {
        return getOption(strategy, priority, resourceId, resourceId, resourceId);
    }

    public static RequestOptions getOption(DiskCacheStrategy strategy, Drawable drawable) {
        return getOption(strategy, Priority.HIGH, drawable, drawable, drawable);
    }

    public static RequestOptions getOption(DiskCacheStrategy strategy, int resourceId) {
        return getOption(strategy, Priority.HIGH, resourceId, resourceId, resourceId);
    }

    public static RequestOptions getOption(Priority priority, Drawable drawable) {
        return getOption(DiskCacheStrategy.ALL, priority, drawable, drawable, drawable);
    }

    public static RequestOptions getOption(Priority priority, int resourceId) {
        return getOption(DiskCacheStrategy.ALL, priority, resourceId, resourceId, resourceId);
    }

    public static RequestOptions getOption(Drawable drawable) {
        return getOption(DiskCacheStrategy.ALL, Priority.HIGH, drawable, drawable, drawable);
    }

    public static RequestOptions getOption(int resourceId) {
        return getOption(DiskCacheStrategy.ALL, Priority.HIGH, resourceId, resourceId, resourceId);
    }
}
