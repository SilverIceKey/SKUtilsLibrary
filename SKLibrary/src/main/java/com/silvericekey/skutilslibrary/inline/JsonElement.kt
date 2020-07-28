package com.silvericekey.skutilslibrary.inline

import com.google.gson.JsonElement

/**
 * <pre>
 *     time   : 2020/07/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
inline fun JsonElement.toString(isClean:Boolean): String = if (isClean)this.toString().let { it.substring(1, it.length - 1) } else this.toString()