package com.silvericekey.skutilslibrary.utils.media;

import android.media.browse.MediaBrowser;

import com.silvericekey.skutilslibrary.base.BaseApplication;

/**
 * 媒体文件相关
 */
public class MediaUtil {
    /**
     * 获取媒体文件工具类单例
     * @return
     */
    public static MediaUtil getInstance(){
        return MediaHolder.mediaUtil;
    }

    /**
     * 初始化
     */
    private MediaUtil() {

    }

    public static class MediaHolder{
        public static volatile MediaUtil mediaUtil = new MediaUtil();
    }
}
