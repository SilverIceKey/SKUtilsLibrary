package com.silvericekey.skutilslibrary.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.silvericekey.skutilslibrary.R

class PictureSelectorUtil {
    companion object {
        @JvmStatic
        private var pictureSelectorUtil: PictureSelectorUtil? = null

        @JvmStatic
        fun getInstance(): PictureSelectorUtil {
            if (pictureSelectorUtil == null) {
                synchronized(PictureSelectorUtil::class.java, {
                    if (pictureSelectorUtil == null) {
                        pictureSelectorUtil = PictureSelectorUtil()
                    }
                })
            }
            return pictureSelectorUtil!!
        }
    }

    private var type: Int = PictureMimeType.ofAll()
    private var theme: Int = R.style.picture_default_style
    private var selectionMode: Int = PictureConfig.SINGLE
    private var signleReturn = true
    private var showCamera = true
    private var isZoomAnime = true
    private var maxSelectNum = 10
    private var minSelectNum = 1
    private var maxVideoSelectNum = 10
    private var minVideoSelectNum = 1
    private var videoMaxSec = 60
    private var videoMinSec = 1
    private var imageSpanCount = 4
    private var recordVideoSecond = 60
    private var isGif = true
    private var cropAspectRatioWide = 16
    private var cropAspectRatioHigh = 9
    private var cutOutQuality = 100
    private var freeStyleCropEnabled = true
    private var isEnableCrop = false

    /**
     * Album Media Type PictureMimeType.ofAll()、ofImage()、ofVideo()、ofAudio()
     */
    fun setType(type: Int): PictureSelectorUtil {
        this.type = type
        return this
    }

    /**
     * Xml Style R.style.picture_default_style、picture_WeChat_style or More Reference Demo
     */
    fun setTheme(theme: Int): PictureSelectorUtil {
        this.theme = theme
        return this
    }

    /**
     * Single or MultiSelect Mode PictureConfig.SINGLE PictureConfig.MULTIPLE
     */
    fun setSelectionMode(selectionMode: Int): PictureSelectorUtil {
        this.selectionMode = selectionMode
        return this
    }

    /**
     * Whether to return directly from PictureConfig.SINGLE Mode
     */
    fun setSingleReturn(singleReturn: Boolean): PictureSelectorUtil {
        this.signleReturn = signleReturn
        return this
    }

    /**
     * Whether the list shows the photo button
     */
    fun showCamera(showCamera: Boolean): PictureSelectorUtil {
        this.showCamera = showCamera
        return this
    }

    /**
     * Select the zoom effect for the image
     */
    fun isZoomAnime(isZoomAnime: Boolean): PictureSelectorUtil {
        this.isZoomAnime = isZoomAnime
        return this
    }

    fun maxSelectNum(maxSelectNum: Int): PictureSelectorUtil {
        this.maxSelectNum = maxSelectNum
        return this
    }

    fun minSelectNum(minSelectNum: Int): PictureSelectorUtil {
        this.minSelectNum = minSelectNum
        return this
    }

    fun maxVideoSelectNum(maxVideoSelectNum: Int): PictureSelectorUtil {
        this.maxVideoSelectNum = maxVideoSelectNum
        return this
    }

    fun minVideoSelectNum(minVideoSelectNum: Int): PictureSelectorUtil {
        this.minVideoSelectNum = minVideoSelectNum
        return this
    }

    fun videoMaxSec(videoMaxSec: Int): PictureSelectorUtil {
        this.videoMaxSec = videoMaxSec
        return this
    }

    fun videoMinSec(videoMinSec: Int): PictureSelectorUtil {
        this.videoMinSec = videoMinSec
        return this
    }

    fun imageSpanCount(imageSpanCount: Int): PictureSelectorUtil {
        this.imageSpanCount = imageSpanCount
        return this
    }

    fun recordVideoSecond(recordVideoSecond: Int): PictureSelectorUtil {
        this.recordVideoSecond = recordVideoSecond
        return this
    }

    fun isGif(isGif: Boolean): PictureSelectorUtil {
        this.isGif = isGif
        return this
    }

    fun cropImageWide(cropImageWide: Int): PictureSelectorUtil {
        this.cropAspectRatioWide = cropImageWide
        return this
    }

    fun cropImageHeigh(cropImageHeigh: Int): PictureSelectorUtil {
        this.cropAspectRatioHigh = cropAspectRatioHigh
        return this
    }

    fun cutOutQuality(cutOutQuality: Int): PictureSelectorUtil {
        this.cutOutQuality = cutOutQuality
        return this
    }

    fun freeStyleCropEnabled(freeStyleCropEnabled: Boolean): PictureSelectorUtil {
        this.freeStyleCropEnabled = freeStyleCropEnabled
        return this
    }
    fun isEnableCrop(isEnableCrop: Boolean): PictureSelectorUtil {
        this.isEnableCrop = isEnableCrop
        return this
    }


    fun openGallery(activity: Activity, result: (result: List<LocalMedia>?) -> Unit, cancel: () -> Unit) {
        PictureSelector.create(activity)
                .openGallery(type)// Album Media Type PictureMimeType.ofAll()、ofImage()、ofVideo()、ofAudio()
                //.openCamera()// Camera Album Media Type PictureMimeType.ofImage()、ofVideo()
                .theme(theme)// Xml Style R.style.picture_default_style、picture_WeChat_style or More Reference Demo
                .selectionMode(selectionMode)// Single or MultiSelect Mode PictureConfig.SINGLE PictureConfig.MULTIPLE
                .isSingleDirectReturn(signleReturn)// Whether to return directly from PictureConfig.SINGLE Mode
                .isCamera(showCamera)// Whether the list shows the photo button
                .isZoomAnim(isZoomAnime)// Select the zoom effect for the image
                .maxSelectNum(maxSelectNum)// Maximum number of options, 9 by default
                .minSelectNum(minSelectNum)// Minimum number of choices
                .maxVideoSelectNum(maxVideoSelectNum)// Maximum number of video options
                .minVideoSelectNum(minVideoSelectNum)// Minimum number of video choices
                .videoMaxSecond(videoMaxSec)// Query how many seconds of video
                .videoMinSecond(videoMinSec)// Query how many seconds of video
                .imageSpanCount(imageSpanCount)// The list displays Numbers per row
                .recordVideoSecond(recordVideoSecond)// The default number of video seconds is 60s
                .isGif(isGif)// Whether to display GIF
                .withAspectRatio(cropAspectRatioWide, cropAspectRatioHigh)// Crop aspect
                .cutOutQuality(cutOutQuality)// Crop output quality defaults to 100
                .freeStyleCropEnabled(freeStyleCropEnabled)// Crop dragged
                .isEnableCrop(isEnableCrop)
                .forResult(object :OnResultCallbackListener<LocalMedia>{
                    override fun onResult(result: MutableList<LocalMedia>?) {
                        result(result)
                    }

                    override fun onCancel() {
                        cancel()
                    }

                })
    }

    fun openGallery(fragment: Fragment,  result:(result: List<LocalMedia>?) -> Unit, cancel: () -> Unit) {
        PictureSelector.create(fragment)
                .openGallery(type)// Album Media Type PictureMimeType.ofAll()、ofImage()、ofVideo()、ofAudio()
                //.openCamera()// Camera Album Media Type PictureMimeType.ofImage()、ofVideo()
                .theme(theme)// Xml Style R.style.picture_default_style、picture_WeChat_style or More Reference Demo
                .imageEngine(GlideEngine.createGlideEngine())
                .selectionMode(selectionMode)// Single or MultiSelect Mode PictureConfig.SINGLE PictureConfig.MULTIPLE
                .isSingleDirectReturn(signleReturn)// Whether to return directly from PictureConfig.SINGLE Mode
                .isCamera(showCamera)// Whether the list shows the photo button
                .isZoomAnim(isZoomAnime)// Select the zoom effect for the image
                .maxSelectNum(maxSelectNum)// Maximum number of options, 9 by default
                .minSelectNum(minSelectNum)// Minimum number of choices
                .maxVideoSelectNum(maxVideoSelectNum)// Maximum number of video options
                .minVideoSelectNum(minVideoSelectNum)// Minimum number of video choices
                .videoMaxSecond(videoMaxSec)// Query how many seconds of video
                .videoMinSecond(videoMinSec)// Query how many seconds of video
                .imageSpanCount(imageSpanCount)// The list displays Numbers per row
                .recordVideoSecond(recordVideoSecond)// The default number of video seconds is 60s
                .isGif(isGif)// Whether to display GIF
                .withAspectRatio(cropAspectRatioWide, cropAspectRatioHigh)// Crop aspect
                .cutOutQuality(cutOutQuality)// Crop output quality defaults to 100
                .freeStyleCropEnabled(freeStyleCropEnabled)// Crop dragged
                .isEnableCrop(isEnableCrop)
                .forResult(object :OnResultCallbackListener<LocalMedia>{
                    override fun onResult(result: MutableList<LocalMedia>?) {
                        result(result)
                    }

                    override fun onCancel() {
                        cancel()
                    }
                })
    }
}