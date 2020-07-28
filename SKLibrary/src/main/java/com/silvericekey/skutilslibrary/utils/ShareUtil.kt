package com.silvericekey.skutilslibrary.utils

import android.content.Intent
import android.net.Uri
import com.silvericekey.skutilslibrary.SKUtilsLibrary

/*
* 分享工具
* 调用系统的分享
* */
class ShareUtil {
    companion object{
        @JvmStatic
        private var shareUtil:ShareUtil? = null
        @JvmStatic
        fun  getInstance():ShareUtil{
            if (shareUtil==null){
                synchronized(ShareUtil::class.java,{
                    if (shareUtil==null){
                        shareUtil = ShareUtil()
                    }
                })
            }
            return shareUtil!!
        }
    }
    /*
    * 分享文字
    * */
    fun shareText(title: String, text: String) {
        var shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        //切记需要使用Intent.createChooser，否则会出现别样的应用选择框，您可以试试
        shareIntent = Intent.createChooser(shareIntent, title)
        SKUtilsLibrary.context!!.startActivity(shareIntent)
    }

    /*
    * 分享单张图片
    * */
    fun shareImage(title: String, uri: Uri) {
        var shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        shareIntent.type = "image/*"
        //切记需要使用Intent.createChooser，否则会出现别样的应用选择框，您可以试试
        shareIntent = Intent.createChooser(shareIntent, title)
        SKUtilsLibrary.context!!.startActivity(shareIntent)
    }

    /*
    * 分享多张图片
    * */
    fun shareImages(title: String, uris: ArrayList<Uri>) {
        var shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND_MULTIPLE
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris)
        shareIntent.type = "image/*"
        //切记需要使用Intent.createChooser，否则会出现别样的应用选择框，您可以试试
        shareIntent = Intent.createChooser(shareIntent, title)
        SKUtilsLibrary.context!!.startActivity(shareIntent)
    }
}