package com.silvericekey.skutilslibrary.utils.io

import com.silvericekey.skutilslibrary.base.BaseApplication
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream

/**
 * <pre>
 *     time   : 2020/07/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class FileIOUtil {
    companion object {
        @JvmStatic
        private var fileIOUtil: FileIOUtil? = null

        @JvmStatic
        fun getInstance(): FileIOUtil {
            if (fileIOUtil == null) {
                synchronized(FileIOUtil::class.java, {
                    if (fileIOUtil == null) {
                        fileIOUtil = FileIOUtil()
                    }
                })
            }
            return fileIOUtil!!
        }
    }

    /**
     * 读取File工具类，返回String
     * @param fileName assets文件名
     * @return
     */
    fun readFile2StringFromAssets(fileName: String): String {
        try {
            val fis = BaseApplication.app.resources?.assets?.open(fileName)
            return convertStreamToString(fis!!)
        } catch (e: Exception) {
            println(e.message)
        }
        return ""
    }

    /**
     * InputStream转String工具类，返回String
     * @param inputStream
     * @return
     */
    @Throws(IOException::class)
    private fun convertStreamToString(inputStream: InputStream): String {
        val result = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var length = 0
        while (inputStream.read(buffer).also({ length = it }) != -1) {
            result.write(buffer, 0, length)
        } // StandardCharsets.UTF_8.name() > JDK 7
        inputStream.close()
        return result.toString("UTF-8")
    }
}