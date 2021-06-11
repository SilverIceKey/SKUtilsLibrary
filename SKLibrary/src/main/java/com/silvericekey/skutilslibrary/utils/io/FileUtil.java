package com.silvericekey.skutilslibrary.utils.io;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件相关
 */
public class FileUtil {
    /**
     * 判断文件是否存在
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean isExists(String filePath, String fileName) {
        return new File(filePath, fileName).exists();
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName
     * @return
     */
    public static boolean isExists(String fileName) {
        return new File(fileName).exists();
    }

    /**
     * 创建文件 如果存在则不创建
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean createFile(String filePath, String fileName) {
        File tmpFile = new File(filePath, fileName);
        if (!tmpFile.exists()) {
            return tmpFile.mkdirs();
        }
        return true;
    }

    /**
     * 创建文件 如果存在则不创建
     *
     * @param fileName
     * @return
     */
    public static boolean createFile(String fileName) {
        File tmpFile = new File(fileName);
        if (!tmpFile.exists()) {
            return tmpFile.mkdirs();
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String filePath, String fileName) {
        File tmpFile = new File(filePath, fileName);
        if (!tmpFile.exists()) {
            return true;
        }
        return tmpFile.delete();
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName) {
        File tmpFile = new File(fileName);
        if (!tmpFile.exists()) {
            return true;
        }
        return tmpFile.delete();
    }
}
