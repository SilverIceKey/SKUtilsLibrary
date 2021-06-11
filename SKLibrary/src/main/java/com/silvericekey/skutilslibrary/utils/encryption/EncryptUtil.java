package com.silvericekey.skutilslibrary.utils.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密解密工具
 */
public class EncryptUtil {
    /**
     * MD5加密
     *
     * @param message
     * @return
     */
    public static String MD5Encode(String message) {
        try {
            return String.format("%32x", new BigInteger(1,
                    MessageDigest.getInstance("MD5").digest(message.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * SHA-1加密
     *
     * @param message
     * @return
     */
    public static String SHA1Encode(String message) {
        try {
            return String.format("%32x", new BigInteger(1,
                    MessageDigest.getInstance("SHA-1").digest(message.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * SHA-256加密
     *
     * @param message
     * @return
     */
    public static String SHA256Encode(String message) {
        try {
            return String.format("%32x", new BigInteger(1,
                    MessageDigest.getInstance("SHA-256").digest(message.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
