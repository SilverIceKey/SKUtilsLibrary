package com.silvericekey.skutilslibrary.uiUtils

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.silvericekey.skutilslibrary.SKUtilsLibrary


/**
 * Created by Administrator on 2017/5/24.
 */

object ImageUtil {
    fun bindImg(url: String, options: RequestOptions): RequestBuilder<*> {
        return bindImg(SKUtilsLibrary.context!!, url, options)
    }

    fun bindImg(context: Context, url: String, options: RequestOptions): RequestBuilder<*> {
        val requestManager = Glide.with(context)
        return requestManager
                .load(url)
                .apply(options)

    }

    fun setRadius(option: RequestOptions, radius: Int): RequestOptions {
        option.transform(RoundedCorners(radius))
        return option
    }

    fun getOption(strategy: DiskCacheStrategy, priority: Priority, placeholder: Int, fallback: Int, error: Int): RequestOptions {
        val option = RequestOptions()
        option.diskCacheStrategy(strategy)
        option.priority(priority)
        option.placeholder(placeholder)
        option.fallback(fallback)
        option.error(error)
        return option
    }

    fun getOption(strategy: DiskCacheStrategy, priority: Priority, placeholder: Drawable, fallback: Drawable, error: Drawable): RequestOptions {
        val option = RequestOptions()
        option.diskCacheStrategy(strategy)
        option.priority(priority)
        option.placeholder(placeholder)
        option.fallback(fallback)
        option.error(error)
        return option
    }

    fun getOption(strategy: DiskCacheStrategy, priority: Priority, drawable: Drawable): RequestOptions {
        return getOption(strategy, priority, drawable, drawable, drawable)
    }

    fun getOption(strategy: DiskCacheStrategy, priority: Priority, resourceId: Int): RequestOptions {
        return getOption(strategy, priority, resourceId, resourceId, resourceId)
    }

    fun getOption(strategy: DiskCacheStrategy, drawable: Drawable): RequestOptions {
        return getOption(strategy, Priority.HIGH, drawable, drawable, drawable)
    }

    fun getOption(strategy: DiskCacheStrategy, resourceId: Int): RequestOptions {
        return getOption(strategy, Priority.HIGH, resourceId, resourceId, resourceId)
    }

    fun getOption(priority: Priority, drawable: Drawable): RequestOptions {
        return getOption(DiskCacheStrategy.ALL, priority, drawable, drawable, drawable)
    }

    fun getOption(priority: Priority, resourceId: Int): RequestOptions {
        return getOption(DiskCacheStrategy.ALL, priority, resourceId, resourceId, resourceId)
    }

    fun getOption(drawable: Drawable): RequestOptions {
        return getOption(DiskCacheStrategy.ALL, Priority.HIGH, drawable, drawable, drawable)
    }

    fun getOption(resourceId: Int): RequestOptions {
        return getOption(DiskCacheStrategy.ALL, Priority.HIGH, resourceId, resourceId, resourceId)
    }
}
