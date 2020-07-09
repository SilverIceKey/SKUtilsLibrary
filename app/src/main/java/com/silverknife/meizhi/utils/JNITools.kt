package com.silverknife.meizhi.utils

/**
 * <pre>
 *     time   : 2020/07/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class JNITools {
    companion object {
        init {
            System.loadLibrary("jniTools")
        }
    }
    external fun getApi():String
}