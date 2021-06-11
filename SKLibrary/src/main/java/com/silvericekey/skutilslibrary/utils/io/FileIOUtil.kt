package com.silvericekey.skutilslibrary.utils.io

import com.silvericekey.skutilslibrary.base.BaseApplication
import java.io.*

/**
 * 文件IO相关
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
     * 从Assets中读取文件，返回String
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
     * 从路径中中读取文件，返回String
     * @param filePath 文件路径
     * @return
     */
    fun readFile2StringFromPath(filePath: String = "", fileName: String): String {
        try {
            val fis = File(filePath + fileName).inputStream()
            return convertStreamToString(fis!!)
        } catch (e: Exception) {
            println(e.message)
        }
        return ""
    }

    /**
     * 数据写入到文件
     * @param filePath 文件路径
     * @return
     */
    fun writeToDisk(filePath: String = "", fileName: String, message: String): Boolean {
        try {
            val tmpFile = File(filePath + fileName)
            if (!tmpFile.exists()){
                tmpFile.mkdirs()
            }
            val fis = tmpFile.outputStream()
            fis.write("\r\n".toByteArray())
            fis.write(message.toByteArray())
            fis.flush()
            fis.close()
            return true
        } catch (e: Exception) {
            println(e.message)
            return false
        }
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