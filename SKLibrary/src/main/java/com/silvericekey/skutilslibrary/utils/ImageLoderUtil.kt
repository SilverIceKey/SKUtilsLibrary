package com.silvericekey.skutilslibrary.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.silvericekey.skutilslibrary.SKUtilsLibrary
import java.io.IOException
import java.io.InputStream


/**
 * Created by Administrator on 2017/5/24.
 */

object ImageLoderUtil {
    fun bindImg(url: String, options: RequestOptions): RequestBuilder<*> {
        return bindImg(SKUtilsLibrary.context!!, url, options)
    }

    fun bindImg(context: Context, url: String, options: RequestOptions): RequestBuilder<*> {
        val requestManager = Glide.with(context)
        return requestManager
                .load(url)
                .apply(options)

    }

    fun bindImg(activity: Activity, url: String, options: RequestOptions): RequestBuilder<*> {
        val requestManager = Glide.with(activity)
        return requestManager
                .load(url)
                .apply(options)

    }

    fun bindImg(fragment: Fragment, url: String, options: RequestOptions): RequestBuilder<*> {
        val requestManager = Glide.with(fragment)
        return requestManager
                .load(url)
                .apply(options)

    }

    fun clear(context: Context, image: View) {
        Glide.with(context).clear(image)
    }

    fun clear(activity: Activity, image: View) {
        Glide.with(activity).clear(image)
    }

    fun clear(fragment: Fragment, image: View) {
        Glide.with(fragment).clear(image)
    }

    class Builder() {
        private var option: RequestOptions
        private var radius = 1
        private var strategy = DiskCacheStrategy.AUTOMATIC
        private var priority = Priority.HIGH
        private var placeholderInt = 0
        private var placeholderDrawable: Drawable? = getLoadDrawable()
        private var FallbackInt = 0
        private var FallbackDrawable: Drawable? = getLoadDrawable()
        private var ErrorInt = 0
        private var ErrorDrawable: Drawable? = getLoadDrawable()

        init {
            option = RequestOptions()
        }

        fun setRadius(radius: Int): Builder {
            this.radius = radius
            return this
        }

        fun setDiskCacheStrategy(strategy: DiskCacheStrategy): Builder {
            this.strategy = strategy
            return this
        }

        fun setPriority(priority: Priority): Builder {
            this.priority = priority
            return this
        }

        fun setPlaceholder(placeholder: Int): Builder {
            this.placeholderInt = placeholder
            return this
        }

        fun setPlaceholder(placeholder: Drawable): Builder {
            this.placeholderDrawable = placeholder
            return this
        }

        fun setFallback(fallback: Int): Builder {
            this.FallbackInt = fallback
            return this
        }

        fun setFallback(fallback: Drawable): Builder {
            this.FallbackDrawable = fallback
            return this
        }

        fun setError(error: Int): Builder {
            this.ErrorInt = error
            return this
        }

        fun setError(error: Drawable): Builder {
            this.ErrorDrawable = error
            return this
        }

        fun getOption(strategy: DiskCacheStrategy, priority: Priority, placeholder: Int, fallback: Int, error: Int): RequestOptions {
            return option.diskCacheStrategy(strategy).priority(priority).placeholder(placeholder).fallback(fallback).error(error)
        }

        fun getOption(strategy: DiskCacheStrategy, priority: Priority, placeholder: Drawable, fallback: Drawable, error: Drawable): RequestOptions {
            return option.diskCacheStrategy(strategy).priority(priority).placeholder(placeholder).fallback(fallback).error(error)
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
            return getOption(DiskCacheStrategy.AUTOMATIC, priority, drawable, drawable, drawable)
        }

        fun getOption(priority: Priority, resourceId: Int): RequestOptions {
            return getOption(DiskCacheStrategy.AUTOMATIC, priority, resourceId, resourceId, resourceId)
        }

        fun getOption(drawable: Drawable): RequestOptions {
            return getOption(DiskCacheStrategy.AUTOMATIC, Priority.HIGH, drawable, drawable, drawable)
        }

        fun getOption(resourceId: Int): RequestOptions {
            return getOption(DiskCacheStrategy.AUTOMATIC, Priority.HIGH, resourceId, resourceId, resourceId)
        }

        fun build(): RequestOptions {
            option = option.transform(RoundedCorners(radius)).diskCacheStrategy(strategy).priority(priority)
            if (placeholderInt == 0) {
                option = option.placeholder(placeholderDrawable)
            } else {
                option = option.placeholder(placeholderInt)
            }
            if (FallbackInt == 0) {
                option = option.fallback(FallbackDrawable)
            } else {
                option = option.fallback(FallbackInt)
            }
            if (ErrorInt == 0) {
                option = option.error(ErrorDrawable)
            } else {
                option = option.error(ErrorInt)
            }
            return option
        }
    }

    fun getLoadDrawable(): Drawable {
        var open: InputStream? = null
        var drawable: Drawable? = null
        try {
            open = SKUtilsLibrary.context!!.getAssets().open("load.jpg")
            drawable = Drawable.createFromStream(open, null)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (open != null) {
                    open.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return drawable!!
    }
}
