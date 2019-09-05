package com.silvericekey.skutilslibrary.utils

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

    class Builder {
        private var option: RequestOptions

        constructor() {
            option = RequestOptions()
        }

        fun setRadius(option: RequestOptions, radius: Int): Builder {
            option.transform(RoundedCorners(radius))
            return this
        }

        fun setDiskCacheStrategy(strategy: DiskCacheStrategy): Builder {
            option.diskCacheStrategy(strategy)
            return this
        }

        fun setPriority(priority: Priority): Builder {
            option.priority(priority)
            return this
        }

        fun setPlaceholder(placeholder: Int): Builder {
            option.placeholder(placeholder)
            return this
        }

        fun setPlaceholder(placeholder: Drawable): Builder {
            option.placeholder(placeholder)
            return this
        }

        fun setFallback(fallback: Int): Builder {
            option.placeholder(fallback)
            return this
        }

        fun setFallback(fallback: Drawable): Builder {
            option.placeholder(fallback)
            return this
        }

        fun setError(error: Int): Builder {
            option.placeholder(error)
            return this
        }

        fun setError(error: Drawable): Builder {
            option.placeholder(error)
            return this
        }

        fun getOption(strategy: DiskCacheStrategy, priority: Priority, placeholder: Int, fallback: Int, error: Int): Builder {
            option.diskCacheStrategy(strategy)
            option.priority(priority)
            option.placeholder(placeholder)
            option.fallback(fallback)
            option.error(error)
            return this
        }

        fun getOption(strategy: DiskCacheStrategy, priority: Priority, placeholder: Drawable, fallback: Drawable, error: Drawable): Builder {
            option.diskCacheStrategy(strategy)
            option.priority(priority)
            option.placeholder(placeholder)
            option.fallback(fallback)
            option.error(error)
            return this
        }

        fun getOption(strategy: DiskCacheStrategy, priority: Priority, drawable: Drawable): Builder {
            return getOption(strategy, priority, drawable, drawable, drawable)
        }

        fun getOption(strategy: DiskCacheStrategy, priority: Priority, resourceId: Int): Builder {
            return getOption(strategy, priority, resourceId, resourceId, resourceId)
        }

        fun getOption(strategy: DiskCacheStrategy, drawable: Drawable): Builder {
            return getOption(strategy, Priority.HIGH, drawable, drawable, drawable)
        }

        fun getOption(strategy: DiskCacheStrategy, resourceId: Int): Builder {
            return getOption(strategy, Priority.HIGH, resourceId, resourceId, resourceId)
        }

        fun getOption(priority: Priority, drawable: Drawable): Builder {
            return getOption(DiskCacheStrategy.ALL, priority, drawable, drawable, drawable)
        }

        fun getOption(priority: Priority, resourceId: Int): Builder {
            return getOption(DiskCacheStrategy.ALL, priority, resourceId, resourceId, resourceId)
        }

        fun getOption(drawable: Drawable): Builder {
            return getOption(DiskCacheStrategy.ALL, Priority.HIGH, drawable, drawable, drawable)
        }

        fun getOption(resourceId: Int): Builder {
            return getOption(DiskCacheStrategy.ALL, Priority.HIGH, resourceId, resourceId, resourceId)
        }

        fun build(): RequestOptions {
            return option
        }
    }
}
